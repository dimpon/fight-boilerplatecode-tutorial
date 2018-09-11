package com.dimpon.tutorals.lombok.builders.builderinbuilder;

import com.dimpon.tutorals.lombok.builders.Transformer;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import static com.dimpon.tutorals.lombok.builders.builderinbuilder.Garage.*;

import static com.dimpon.tutorals.lombok.builders.builderinbuilder.Garage.Auto.Brand.*;

/**
 * @author Dmitrii Ponomarev
 */
@Slf4j
public class Start {
    public static void main(String[] args) {

        Garage garage = Garage.builder()
                .owner("John Doe")
                .car(Auto.of()
                        .brand(VW)
                        .model("Polo")
                        .vin(342342342)
                        .create())
                .car(Auto.of()
                        .brand(SHKODA)
                        .model("Oktavia")
                        .vin(21321434)
                        .create())
                .build();

        log.info(garage.autosDetails());

        //////////////////////////////////////////////

        BiFunction<TradeRequest,FixData,TradeRequest> complex = (tradeRequest, fixData) -> {
            if(fixData.params.size()==3){
                tradeRequest.bank("Citi");
            }
            return tradeRequest;
        };

        TradeRequest tr = Transformator.<TradeRequest,FixData>builder()
                .source(new FixData())
                .operation((tradeRequest, fixData) -> tradeRequest.currency(fixData.params.get("currency")))
                .operation((tradeRequest, fixData) -> tradeRequest.amount(fixData.params.get("amount")))
                .operation((tradeRequest, fixData) -> tradeRequest.bank(fixData.params.get("bank")))
                .operation(complex)
                .build()
                .transform(new TradeRequest());

        log.info(tr.toString());

    }

    @Getter
    private static class FixData{
        Map<String,String> params = new HashMap<String,String>(){{
            put("currency","EUR");
            put("amount","1000000");
            put("bank","Postbank");
        }};
    }

    @Data
    @Accessors(chain = true, fluent = true)
    private static class TradeRequest{
        String currency, amount, bank;
    }
}
