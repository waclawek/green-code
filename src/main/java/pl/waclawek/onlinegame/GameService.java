package pl.waclawek.onlinegame;


import org.openapi.quarkus.onlinegame_json.api.DefaultApi;
import org.openapi.quarkus.onlinegame_json.model.Clan;
import org.openapi.quarkus.onlinegame_json.model.Players;

import java.util.*;
import java.util.stream.Collectors;

public class GameService implements DefaultApi {
    @Override
    public List<List<Clan>> calculate(Players players) {

        Comparator<Clan> clanComparator = (c1, c2) -> {
            if (!Objects.equals(c1.getPoints(), c2.getPoints())) {
                return Integer.compare(c2.getPoints(), c1.getPoints());
            } else {
                return Integer.compare(c1.getNumberOfPlayers(), c2.getNumberOfPlayers());
            }
        };

        var clanList = players.getClans().stream().sorted(clanComparator).collect(Collectors.toList());

        return findClanCombinations(players.getGroupCount(), clanList);
    }

    public static List<List<Clan>> findClanCombinations(int groupCount, List<Clan> clanList) {
        List<List<Clan>> playerOrder = new ArrayList<>();
        int playersRemaining = clanList.stream().mapToInt(Clan::getNumberOfPlayers).sum();

        while (playersRemaining > 0) {
            int playersInGroup = Math.min(groupCount, playersRemaining);
            List<Clan> group = new ArrayList<>();

            for (int i = 0; i < clanList.size(); i++) {
                Clan clan = clanList.get(i);
                if (clan.getNumberOfPlayers() <= playersInGroup) {
                    group.add(clan);
                    playersInGroup -= clan.getNumberOfPlayers();
                    playersRemaining -= clan.getNumberOfPlayers();
                    clanList.remove(i);
                    i--;
                }
            }
            playerOrder.add(group);
        }
        return playerOrder;
    }

}





