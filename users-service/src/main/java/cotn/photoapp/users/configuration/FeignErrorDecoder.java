package cotn.photoapp.users.configuration;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author kaustavbasu
 * @Date 7/24/20
 * @Time 2:36 PM
 */
public class FeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()) {
            case 400:
                // Do something
                // return new BadRequestException();
                break;
            case 404: {
                if (methodKey.contains("getAlbums")) {

                    return new ResponseStatusException(HttpStatus.valueOf(response.status()), "Users Album not found");
                }
                break;
            }
            default:
                return new Exception(response.reason());
        }
        return null;
    }
}
