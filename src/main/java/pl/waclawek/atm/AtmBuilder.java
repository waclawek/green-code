package pl.waclawek.atm;

import org.openapi.quarkus.atmservice_json.model.ATM;

public class AtmBuilder{

    private Integer atmId;
    private Integer region;


    public AtmBuilder() {}

    public AtmBuilder setAtmId(Integer atmId) {
        this.atmId = atmId;
        return this;
    }

    public AtmBuilder setRegion(Integer region) {
        this.region = region;
        return this;
    }

    public ATM build(){
        var atm = new ATM();
        atm.setRegion(this.region);
        atm.setAtmId(this.atmId);
        return atm;
    }

}
