package hexlet.code.dto.urls;

import hexlet.code.dto.BasePage;
import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
public class UrlsPage extends BasePage {

    private List<Url> urls;
    private Map<Long, List<UrlCheck>> urlChecks;
    private int pageNumber;

    public Map<Url, UrlCheck> getChecks() {
        Map<Url, UrlCheck> latestChecks = new HashMap<>();
        if (urls != null && urlChecks != null) {
            for (Url url : urls) {
                List<UrlCheck> checks = urlChecks.get(url.getId());
                if (checks != null && !checks.isEmpty()) {
                    // Предполагаем, что список checks отсортирован по дате убыванию: самая свежая — первая
                    latestChecks.put(url, checks.get(0));
                }
            }
        }
        return latestChecks;
    }
}
