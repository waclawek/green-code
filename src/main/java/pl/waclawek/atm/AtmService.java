package pl.waclawek.atm;

import jakarta.ws.rs.POST;
import org.openapi.quarkus.atmservice_json.api.DefaultApi;
import org.openapi.quarkus.atmservice_json.model.ATM;
import org.openapi.quarkus.atmservice_json.model.Task;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class AtmService implements DefaultApi {

    @Override
    @POST
    public List<ATM> calculate(List<Task> task) {

        List<AtmDto> atmList = task.stream()
                .map(mapToDomain())
                .sorted(Comparator
                        .comparing(AtmDto::getRegion)
                        .thenComparing(AtmDto::getRequestTypeValue)
                )
                .distinct()
                .toList();

        return atmList.stream()
                .map(mapToExternal())
                .toList();
    }

    Function<Task, AtmDto> mapToDomain() {
        return task -> new AtmDto.AtmDtoBuilder()
                .setAtmId(task.getAtmId())
                .setRegion(task.getRegion())
                .setRequestTypeValue(RequestTypeDtoEnum.mapFromExternal(task.getRequestType()).priority)
                .build();
    }

    Function<AtmDto, ATM> mapToExternal() {
        return atmDto -> new AtmBuilder()
                .setRegion(atmDto.region)
                .setAtmId(atmDto.getAtmId())
                .build();
    }
}
