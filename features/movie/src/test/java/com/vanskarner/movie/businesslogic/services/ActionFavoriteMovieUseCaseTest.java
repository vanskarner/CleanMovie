package com.vanskarner.movie.businesslogic.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.vanskarner.movie.businesslogic.repository.FakeRepositoryFactory;
import com.vanskarner.movie.businesslogic.entities.MovieBOBuilder;
import com.vanskarner.movie.businesslogic.entities.MovieBO;
import com.vanskarner.movie.businesslogic.error.MovieError;
import com.vanskarner.movie.businesslogic.repository.FakeMovieLocalRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ActionFavoriteMovieUseCaseTest {

    FakeMovieLocalRepository fakeMovieLocalRepository;
    ActionFavoriteMovieUseCase useCase;

    @Before
    public void setUp() {
        fakeMovieLocalRepository = FakeRepositoryFactory.createMovieLocalRepository();
        MovieErrorFilter domainErrorFilter = new MockMovieErrorFilter();

        useCase = new ActionFavoriteMovieUseCase(fakeMovieLocalRepository, domainErrorFilter);
    }

    @After
    public void tearDown() {
        fakeMovieLocalRepository.clear();
    }

    @Test
    public void execute_withUnregisteredItem_savedItem() {
        MovieBO unregisteredItem = MovieBOBuilder.getInstance()
                .withId(1)
                .build();
        boolean like = useCase.execute(MovieMapper.convert(unregisteredItem)).get();
        int actualNumberItems = fakeMovieLocalRepository.getList().size();
        int expectedNumberItems = 1;

        assertTrue(like);
        assertEquals(expectedNumberItems, actualNumberItems);
    }

    @Test
    public void execute_withRegisteredItem_deletedItem() {
        MovieBO registeredItem = MovieBOBuilder.getInstance()
                .withId(1)
                .build();
        fakeMovieLocalRepository.addItem(registeredItem);
        boolean like = useCase.execute(MovieMapper.convert(registeredItem)).get();
        int actualNumberItems = fakeMovieLocalRepository.getList().size();
        int expectedNumberItems = 0;

        assertFalse(like);
        assertEquals(expectedNumberItems, actualNumberItems);
    }

    @Test(expected = MovieError.FavoriteLimit.class)
    public void execute_withUnregisteredItemAndExceededCapacity_exception() {
        List<MovieBO> list = new ArrayList<>();
        list.add(MovieBOBuilder.getInstance()
                .withId(1)
                .build());
        list.add(MovieBOBuilder.getInstance()
                .withId(2)
                .build());
        fakeMovieLocalRepository.setList(list);
        MovieBO unregisteredItem = MovieBOBuilder.getInstance()
                .withId(3)
                .build();
        useCase.execute(MovieMapper.convert(unregisteredItem)).get();
    }

}