package test.mobicloud.stashit;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by venkat kishore on 07-12-2017.
 */

public interface RequestInterface {

    @GET("android/jsonandroid")
    Call<JSONResponse> getJSON();
}
