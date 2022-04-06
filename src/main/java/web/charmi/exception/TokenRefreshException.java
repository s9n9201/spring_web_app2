package web.charmi.exception;

public class TokenRefreshException extends RuntimeException {
    public TokenRefreshException(String token, String message) {
        super(String.format("[ Charmi ] Failed for [ %s ]: %s", token, message));
    }
}
