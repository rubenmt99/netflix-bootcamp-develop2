package integration;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import com.everis.d4i.tutorial.json.CategoryRest;
import com.everis.d4i.tutorial.json.ChapterRest;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.github.javafaker.Faker;


public class GetMocksForTesting {

	Faker faker = new Faker();
	
	public static List<CategoryRest> getMockListCategoryRest() {
		List<CategoryRest> categoryList = new ArrayList<>();
		categoryList.add(new CategoryRest(1L, "category 1"));
		categoryList.add(new CategoryRest(1L, "category 2"));
		categoryList.add(new CategoryRest(1L, "category 3"));
		categoryList.add(new CategoryRest(1L, "category 4"));
		return categoryList;
	}



	public static List<ChapterRest> getMockListChapterRest() {
		List<ChapterRest> chapterRestList = new ArrayList<>();
		chapterRestList.add(new ChapterRest(1L, (short) 4, "Capitulo 1", (short) 12));
		chapterRestList.add(new ChapterRest(2L, (short) 5, "Capitulo 2", (short) 30));

		return chapterRestList;
	}



	public static List<TvShowRest> getMockListTvShowRest() {
		List<TvShowRest> showRestList = new ArrayList<>();

		Year yearYear = Year.of(2003);

		showRestList.add(new TvShowRest(1L, "Aquí no hay quien viva", "Comunidad de vecinos", "Descripcion larga",
				yearYear, (byte) 7, getMockListCategoryRest().get(0), "Adertising", getMockListChapterRest()));

		showRestList.add(new TvShowRest(1L, "Vikingos", "Guerra Vikinga", "Descripcion larga",
				yearYear, (byte) 5, getMockListCategoryRest().get(1), "Adertising", getMockListChapterRest()));

		return showRestList;
	}

}
