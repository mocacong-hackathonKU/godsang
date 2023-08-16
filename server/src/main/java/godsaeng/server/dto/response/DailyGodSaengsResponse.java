package godsaeng.server.dto.response;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class DailyGodSaengsResponse {

    private List<DailyGodSaengResponse> dailyGodsaengs;
}
