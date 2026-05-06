/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package barbosa.com.apijwt.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * TokenService - Serviço responsável pela geração e validação de tokens JWT
 * 
 * Esta classe fornece métodos para:
 * - Gerar novos tokens JWT com informações do usuário
 * - Validar tokens JWT recebidos nas requisições
 * - Extrair claims (informações) dos tokens válidos
 * 
 * @author Usuario
 */
@Service
public class TokenService {
    
    // Injeta o valor da propriedade 'api.security.token.secret' do arquivo application.properties
    // Esta é a chave secreta usada para assinar e validar os tokens JWT
    @Value("${api.security.token.secret}")
    private String secret;
    
    
    /**
     * Gera a chave secreta HMAC-SHA a partir da string secreta em Base64
     * 
     * O token JWT utiliza essa chave para assinatura criptográfica.
     * O método decodifica a chave secreta de Base64 para bytes
     * e cria uma SecretKey compatível com o algoritmo HMAC-SHA
     * 
     * @return SecretKey - chave secreta HMAC-SHA para assinar e validar tokens
     */
    private SecretKey getSignKey() {
        // Decodifica a chave secreta de Base64 para um array de bytes
        byte[] keyBytes = Decoders.BASE64.decode(this.secret);
        // Cria uma chave HMAC-SHA válida para uso em tokens JWT
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
    
    
    /**
     * Gera um novo token JWT com as informações do usuário
     * 
     * O token contém:
     * - Subject (sub): identificador do usuário (email)
     * - Issued At (iat): data/hora de criação do token
     * - Expiration (exp): data/hora de expiração (5 minutos após criação)
     * - Signature: assinatura criptográfica com a chave secreta
     * 
     * @return String - token JWT codificado em Base64
     */
    public String gerarToken() {
        return Jwts.builder()
                // Define o subject (identificador do usuário)
                .subject("iago.teste@teste.com")
                // Define quando o token foi criado
                .issuedAt(new Date())
                // Define quando o token expira (5 minutos = 300000 milissegundos)
                .expiration(new Date(System.currentTimeMillis() + 300000))
                // Assina o token com a chave secreta HMAC-SHA
                .signWith(getSignKey())
                // Converte o token construído para a sua forma compacta (String)
                .compact();
    }
    
    /**
     * Valida se um token JWT é legítimo e não expirou
     * 
     * A validação verifica:
     * - Se a assinatura do token é válida (foi assinado com a chave secreta correta)
     * - Se o token não foi alterado ou corrompido
     * - Se o token não expirou
     * 
     * @param token String - token JWT a ser validado
     * @return boolean - true se o token é válido, false caso contrário
     */
    public boolean validarToken(String token) {
        try {
            // Cria um parser JWT com a chave secreta para validação
            Jwts.parser()
                    .setSigningKey(getSignKey())
                    .build()
                    // Analisa e valida o token (lança exceção se inválido ou expirado)
                    .parseClaimsJws(token);
            // Se chegou aqui, o token é válido
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // Se qualquer exceção ocorrer, o token é inválido ou expirou
            return false;
        }
    }
    
    /**
     * Extrai as informações (claims) de um token JWT válido
     * 
     * Os claims incluem:
     * - subject: identificador do usuário
     * - issuedAt: data/hora de criação
     * - expiration: data/hora de expiração
     * - e outros claims customizados se houver
     * 
     * IMPORTANTE: Este método assume que o token já foi validado.
     * Certifique-se de chamar validarToken() antes de usar este método.
     * 
     * @param token String - token JWT válido
     * @return Claims - objeto contendo todas as informações do token
     */
    public Claims extrairClaims(String token) {
        // Analisa o token com a chave secreta
        return Jwts.parser()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                // Retorna o corpo do token (claims)
                .getBody();
    }
    
}


