package cotn.photoapp.users.configuration;

import cotn.photoapp.users.model.AlbumDetailsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author kaustavbasu
 * @Date 7/22/20
 * @Time 11:01 PM
 */
@FeignClient(value = "albums-ws",fallbackFactory = AlbumsFallbackFactory.class)
public interface AlbumServiceClient {

    @GetMapping("/users/{id}/albums")
    public List<AlbumDetailsDTO> getAlbums(@PathVariable String id);
}
