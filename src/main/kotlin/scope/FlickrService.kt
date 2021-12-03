package scope

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//https://www.flickr.com
// /services/rest/?method=flickr.photos.search&api_key=f28b2baebe82c4edaf7c1f2eebaa8fec&format=json&nojsoncallback=1
//&text=Moscow
interface FlickrService {

    @GET("/services/rest/?method=flickr.photos.search&api_key=f28b2baebe82c4edaf7c1f2eebaa8fec&format=json&nojsoncallback=1")
    fun search(
        @Query("text")
        text: String,
        @Query("page")
        page: Int
    ) : Call<Result>

}