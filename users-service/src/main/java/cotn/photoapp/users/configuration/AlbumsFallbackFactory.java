package cotn.photoapp.users.configuration;

import cotn.photoapp.users.model.AlbumDetailsDTO;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author kaustavbasu
 * @Date 7/24/20
 * @Time 6:26 PM
 */
@Component
public class AlbumsFallbackFactory implements FallbackFactory<AlbumServiceClient> {

    @Override
    public AlbumServiceClient create(Throwable throwable) {
        return new AlbumsFallback(throwable);
    }
}
