package collioni.douglas.consultacep;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by lacomp01 on 17/09/2016.
 */
public interface CorreioService {
    @GET("cep/{cep}")
    public Call<Endereco> getEndereco(@Path("cep") String cep);
}
