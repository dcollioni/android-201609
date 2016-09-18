package collioni.douglas.escolas;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by lacomp01 on 17/09/2016.
 */
public interface EscolaService {
    @GET("schools")
    public Call<List<Escola>> listarEscolas();
}
