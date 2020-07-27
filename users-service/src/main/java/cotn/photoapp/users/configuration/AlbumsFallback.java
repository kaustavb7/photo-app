package cotn.photoapp.users.configuration;

import cotn.photoapp.users.model.AlbumDetailsDTO;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kaustavbasu
 * @Date 7/24/20
 * @Time 6:39 PM
 */
public class AlbumsFallback implements AlbumServiceClient {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Throwable throwable;

    public AlbumsFallback(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public List<AlbumDetailsDTO> getAlbums(String id) {
        // TODO Auto-generated method stub

        if (throwable instanceof FeignException && ((FeignException) throwable).status() == 404) {
            logger.error("404 error took place when getAlbums was called with userId: " + id + ". Error message: "
                    + throwable.getLocalizedMessage());
        } else {
            logger.error("Other error took place: " + throwable.getLocalizedMessage());
        }

        return new ArrayList<>();
    }
}
