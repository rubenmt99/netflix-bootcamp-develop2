package FakerObjects;

import com.everis.d4i.tutorial.entities.Award;
import com.everis.d4i.tutorial.entities.Category;
import com.everis.d4i.tutorial.entities.Season;
import com.everis.d4i.tutorial.entities.TvShow;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Component
public class FakerObject {

    Faker faker = new Faker();

    public List<TvShow> fakerTvShows(Integer number){
        List<TvShow> myShows = new ArrayList<>();
//        List<Category> category = new ArrayList<>();
//        List<Award> awards = new ArrayList<>();
//        List<Season> seasons = new ArrayList<>();

        for (int i = 0; i<=number; i++){
            TvShow tvShow = new TvShow();
            tvShow.setId((long) i);
            tvShow.setCategory(null);
            tvShow.setAwards(null);
            tvShow.setSeasons(null);
            tvShow.setName(faker.funnyName().name());
            tvShow.setYear(Year.parse(faker.number().digits(4)));
            tvShow.setRecommendedAge((byte) faker.number().numberBetween(7,18));
            tvShow.setAdvertising(faker.funnyName().name());
            tvShow.setShortDescription(faker.company().name());
            tvShow.setLongDescription(faker.gameOfThrones().quote());
            myShows.add(tvShow);
        }
        return myShows;
    }


}
