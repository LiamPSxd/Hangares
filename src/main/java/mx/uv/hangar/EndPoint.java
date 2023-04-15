package mx.uv.hangar;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import https.t4is_uv_mx.hangares.DELETEHangarRequest;
import https.t4is_uv_mx.hangares.DELETEHangarResponse;
import https.t4is_uv_mx.hangares.GETHangarRequest;
import https.t4is_uv_mx.hangares.GETHangarResponse;
import https.t4is_uv_mx.hangares.POSTHangarRequest;
import https.t4is_uv_mx.hangares.POSTHangarResponse;
import https.t4is_uv_mx.hangares.PUTHangarRequest;
import https.t4is_uv_mx.hangares.PUTHangarResponse;

@Endpoint
public class EndPoint{
    @Autowired
    private IHangar iHangar;
    private JSONObject json;

    @PayloadRoot(localPart = "GETHangarRequest", namespace = "https://t4is.uv.mx/hangares")
    @ResponsePayload
    public GETHangarResponse GET(@RequestPayload GETHangarRequest request){
        GETHangarResponse response = new GETHangarResponse();
        String res = "{\"message\": \"Exitoso\", \"data\": [";

        if(request.getId() == -1)
            for(Hangar hangar : iHangar.findAll()) res += hangar.toString();
        else if(request.getId() > -1){
            Hangar hangar = iHangar.findById(request.getId()).get();

            if(hangar != null) res += hangar.toString();
            else res = "{\"message\": \"Fallido\", \"data\": [";
        }

        json = new JSONObject(res + "]}");
        response.setReturn(json.toString());

        return response;
    }

    @PayloadRoot(localPart = "POSTHangarRequest", namespace = "https://t4is.uv.mx/hangares")
    @ResponsePayload
    public POSTHangarResponse POST(@RequestPayload POSTHangarRequest request){
        POSTHangarResponse response = new POSTHangarResponse();

        Hangar hangar = new Hangar();
        hangar.setCapacidad(request.getCapacidad());
        hangar.setUbicacion(request.getUbicacion());
        iHangar.save(hangar);

        if(iHangar.findById(hangar.getId()).get() != null) json = new JSONObject("{\"message\": \"Exitoso\"}");
        else json = new JSONObject("{\"message\": \"Fallido\"}");

        response.setReturn(json.toString());

        return response;
    }

    @PayloadRoot(localPart = "PUTHangarRequest", namespace = "https://t4is.uv.mx/hangares")
    @ResponsePayload
    public PUTHangarResponse PUT(@RequestPayload PUTHangarRequest request){
        PUTHangarResponse response = new PUTHangarResponse();
        Hangar hangar = iHangar.findById(request.getId()).get();

        if(hangar != null){
            hangar.setCapacidad(request.getCapacidad());
            hangar.setUbicacion(request.getUbicacion());
            iHangar.save(hangar);

            json = new JSONObject("{\"message\": \"Exitoso\"}");
        }else json = new JSONObject("{\"message\": \"Fallido\"}");

        response.setReturn(json.toString());

        return response;
    }

    @PayloadRoot(localPart = "DELETEHangarRequest", namespace = "https://t4is.uv.mx/hangares")
    @ResponsePayload
    public DELETEHangarResponse DELETE(@RequestPayload DELETEHangarRequest request){
        DELETEHangarResponse response = new DELETEHangarResponse();
        Hangar hangar = iHangar.findById(request.getId()).get();

        if(hangar != null){
            iHangar.delete(hangar);

            json = new JSONObject("{\"message\": \"Exitoso\"}");
        }else json = new JSONObject("{\"message\": \"Fallido\"}");

        response.setReturn(json.toString());

        return response;
    }
}