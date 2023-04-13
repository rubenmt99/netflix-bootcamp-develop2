package unitarios;

import FakerObjects.FakerObject;
import com.everis.d4i.tutorial.entities.Award;
import com.everis.d4i.tutorial.entities.Category;
import com.everis.d4i.tutorial.entities.Season;
import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.CategoryRest;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.repositories.CategoryRepository;
import com.everis.d4i.tutorial.repositories.TvShowRepository;
import com.everis.d4i.tutorial.services.impl.TvShowServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@SpringBootTest(classes = TvShow.class)
public class TvShowServiceImplTest {

    @InjectMocks
    TvShowServiceImpl tvShowService;

    @Mock
    TvShowRepository tvShowRepository;

    @Mock
    CategoryRepository categoryRepository;

    private ModelMapper modelMapper = new ModelMapper();

    private static List<TvShow> tvShowList;

    @BeforeEach
    public void setUp() {
        tvShowList = new FakerObject().fakerTvShows(10);
    }


    @Test
    public void getTvShowsByCategoryId() throws NetflixException {
        when(tvShowRepository.findByCategoryId(1L)).thenReturn(tvShowList);
        List<TvShowRest> myShowsRest = tvShowService.getTvShowsByCategory(1L);

        List<TvShow> myShows = myShowsRest.stream()
                .map(tvShowRest -> modelMapper.map(tvShowRest, TvShow.class))
                .collect(Collectors.toList());

        assertEquals(tvShowList, myShows);
        assertEquals(11,myShows.size());
    }

    @Test
    public void getTvShowsById() throws NetflixException {
        when(tvShowRepository.findById(1L)).thenReturn(Optional.ofNullable(tvShowList.get(1)));
        TvShowRest myShowsRest = tvShowService.getTvShowById(1L);

        TvShow myShows = modelMapper.map(myShowsRest,TvShow.class);

        assertEquals(tvShowList.get(1), myShows);
    }

    @Test
    public void createTvShow() throws NetflixException {
        when(tvShowRepository.save(tvShowList.get(0))).thenReturn(tvShowList.get(0));
        TvShowRest myShowsRest = tvShowService.createTvShow(tvShowList.get(0));

        TvShow myShow = modelMapper.map(myShowsRest,TvShow.class);

        assertEquals(tvShowList.get(0), myShow);
    }

    @Test
    public void addCategoryShow() throws NetflixException {
        List<Category> newCategory = new ArrayList<>();
        Category category = new Category(1L,"Testing");
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(tvShowRepository.findById(1L)).thenReturn(Optional.ofNullable(tvShowList.get(1)));

        TvShow tvShow1 = tvShowRepository.findById(1L).orElse(null);
        Category category1 = categoryRepository.findById(1L).orElse(null);

        if (tvShow1 != null) {
            tvShow1.setCategory(newCategory);
        }

        tvShow1.getCategory().add(category1);

        assertNotNull(tvShowList.get(1).getCategory());
        assertEquals(tvShowList.get(1).getCategory().get(0).getName(),"Testing");
    }

    @Test
    public void deleteTvShow() throws NetflixException {
        doAnswer(invocationOnMock -> {
            TvShow tvShow = invocationOnMock.getArgument(0);
            verify(tvShowRepository, times(1)).delete(tvShow);
            return null;
        }).when(tvShowRepository).delete(any(TvShow.class));

        tvShowService.deleteShow(4L);

        // caso de fallo
//        doThrow(new NetflixException("TvShow not found")).when(tvShowRepository).delete(any(TvShow.class));
//        assertThrows(NetflixException.class, () -> tvShowService.deleteShow(100L));
    }



}
