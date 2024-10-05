package ph.edu.cdsga.sms.ums.utils.jwt;

import io.jsonwebtoken.*;
import org.json.JSONException;
import org.json.JSONObject;
import ph.edu.cdsga.sms.ums.entity.account.UserProfile;
import ph.edu.cdsga.sms.ums.entity.account.UserToken;
import ph.edu.cdsga.sms.ums.exception.JwtTokenMalformedException;
import ph.edu.cdsga.sms.ums.exception.JwtTokenMissingException;

import java.util.Base64;
import java.util.Date;
import java.util.List;

public final class JwtUtility {

    public static String generateToken(UserProfile userProfile, List<String> rolesList, String url){
        return getString(userProfile, rolesList, url);
    }

    public static UserToken populateUserToken(UserProfile userProfile, String accessToken, String scope){
        return UserToken.builder()
                .accessToken(accessToken)
                .expiresIn("300")
                .refreshExpiresIn("0")
                .tokenType(JwtProperties.JWT_BEARER)
                .notBeforePolicy("0")
                .scope(scope)
                .userProfile(userProfile)
                .build();
    }

    public static String getString(UserProfile userProfile, List<String> rolesList, String url) {
        Claims claims = Jwts.claims().setSubject(userProfile.getEmail());
        Date exp = new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME);
        return Jwts.builder()
                .setHeaderParam("typ", JwtProperties.HEADER_STRING)
                .setClaims(claims)
                .claim("userId", userProfile.getUserId())
                .claim("firstName", userProfile.getFirstName())
                .claim("username", userProfile.getUsername())
                .claim("roles", rolesList)
                .setIssuer(url)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS512, JwtProperties.JWT_SECRET.getBytes())
                .compact();
    }

    public static Claims getAllClaimsFromToken(final String token) {
        return Jwts.parser().setSigningKey(JwtProperties.JWT_SECRET.getBytes()).parseClaimsJws(token).getBody();
    }


    public static boolean checkTokenExpiration(String token){
        Claims claim = getAllClaimsFromToken(token);
        if(claim.getExpiration().before(new Date())){
            //logErrorMessage(this.getClass().toString(), "checkTokenExpiration", "Expired JWT token");
            return true;
        }
        //logSuccessMessage(this.getClass().toString(), "checkTokenExpiration", "OK");
        return false;
    }

    public static String getTokenValue(String v_token, String v_value) throws JSONException {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String[] chunks = v_token.split("\\.");
        String payload = new String(decoder.decode(chunks[1]));
        JSONObject jObject  = new JSONObject(payload);
        return jObject.getString(v_value).replaceAll("[\\[\\]\"]", "");
    }

    public void validateToken(final String token) throws JwtTokenMalformedException, JwtTokenMissingException {
        try {
            Jwts.parser().setSigningKey(JwtProperties.JWT_SECRET.getBytes()).parseClaimsJws(token);
        } catch (SignatureException ex) {
            throw new JwtTokenMalformedException("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            throw new JwtTokenMalformedException("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new JwtTokenMalformedException("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new JwtTokenMalformedException("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new JwtTokenMissingException("JWT claims string is empty.");
        }
    }
}
