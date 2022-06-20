import com.epam.cinema.dao.implementation.DAOMovieImpl;
import com.epam.cinema.enity.Movie;
import com.epam.cinema.service.implementation.MovieServiceImpl;
import com.epam.cinema.service.ServiceFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class MovieServiceImplTest {
    @InjectMocks
    MovieServiceImpl movieService;
    @Mock
    private DAOMovieImpl daoMovie;


    @Test
    public void addMovieTest() {
        Movie movie = new Movie("George Life", new Date(1693083600000L));
        movie.setCountry("Georgia");
        assertTrue(ServiceFactory.getMovieService().addMovie(movie));
    }

    @Test
    public void findMovieByNameTest() {
        Movie movie = ServiceFactory.getMovieService().findMovieByName("George Life");
        assertNotNull(movie);
    }

    @Test
    public void findMovieByNamePatternTest() {
        List<Movie> movieList = ServiceFactory.getMovieService().findMoviesByNamePattern("o");
        movieList.forEach(System.out::println);
        assertNotNull(movieList);
    }

    @Test
    public void updateMovieTest() {
        Movie movie = ServiceFactory.getMovieService().findMovieByName("George Life");
        movie.setDirection("Krivoy Pol");
        assertTrue(ServiceFactory.getMovieService().updateMovie(movie));
    }

    @Test
    public void deleteMovieTest() {
        Movie movie = ServiceFactory.getMovieService().findMovieByName("George Life");
    }

    @Test
    public void Test() {
        Movie expectedResult = new Movie();
        Mockito.when(daoMovie.getMovieByName("George Life"))
                .thenReturn(expectedResult);

        Movie movie = movieService.findMovieByName("George Life");

        assertEquals(expectedResult, movie);
        Mockito.verify(daoMovie).getMovieByName("George Life");
    }
}
