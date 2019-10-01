package CSCC.processing;

import CSCC.data.Event;
import org.junit.Test;

public class EventHandlerTest {

    @Test
    public void getInstance() {
    }

    @Test
    public void createEventFromJson() {
    }

    @Test
    public void createEventTable() {
    }

    @Test
    public void insertCurrentEventIntoDB() {
    }

    @Test
    public void commitChangesAndShutdownDB() {
    }

    @Test
    public void shouldReturnEmptyEventWhenJsonHasntBeenRead() {
        //Given
        EventHandler sut = EventHandler.getInstance();

        //When
        Event emptyEvent = sut.getEvent();

        //Then
        assert emptyEvent.equals(new Event());
    }

    @Test
    public void shouldReturnEventParsedFromJsonWhenItWasReadSuccessfully() {
        //Given
        EventHandler sut = EventHandler.getInstance();
        Event testEvent = new Event("abc","STARTED","1491377495216");

        //When
        sut.createEventFromJson("{\"id\":\"abc\", \"state\":\"STARTED\", \"timestamp\":1491377495216}");
        Event actual = sut.getEvent();

        //Then
        assert actual.equals(testEvent);
    }

}