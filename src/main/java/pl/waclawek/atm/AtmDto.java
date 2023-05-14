package pl.waclawek.atm;

import java.util.Objects;

public class AtmDto {

    public int region;
    public int atmId;
    public int requestTypeValue;

    public AtmDto(int region, int atmId, int requestTypeValue) {
        this.region = region;
        this.atmId = atmId;
        this.requestTypeValue = requestTypeValue;
    }

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public int getAtmId() {
        return atmId;
    }

    public void setAtmId(int atmId) {
        this.atmId = atmId;
    }

    public int getRequestTypeValue() {
        return requestTypeValue;
    }

    public void setRequestTypeValue(int requestTypeValue) {
        this.requestTypeValue = requestTypeValue;
    }

    @Override
    public boolean equals(Object atm) {
        if (this == atm) return true;
        if (atm == null || getClass() != atm.getClass()) return false;
        AtmDto atmDto = (AtmDto) atm;
        return region == atmDto.region && atmId == atmDto.atmId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(region, atmId);
    }

    public static class AtmDtoBuilder {

        private int atmId;
        private int region;

        private int requestTypeValue;

        public AtmDtoBuilder setAtmId(int atmId) {
            this.atmId = atmId;
            return this;
        }

        public AtmDtoBuilder setRegion(int region) {
            this.region = region;
            return this;
        }

        public AtmDtoBuilder setRequestTypeValue(int requestTypeValue) {
            this.requestTypeValue = requestTypeValue;
            return this;
        }

        public AtmDto build(){
            return new AtmDto(this.region, this.atmId, this.requestTypeValue);
        }
    }
}
