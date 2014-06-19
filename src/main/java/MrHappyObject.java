import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//This is the event generator(generates events)
public class MrHappyObject {
    private Mood _mood = Mood.HAPPY;
    private List _listeners = new ArrayList();

    //Will fire event of an specific type when called
    public synchronized void receivePinch() {
        if (_mood == Mood.HAPPY) {
            _mood = Mood.ANNOYED;
            _fireMoodEvent();
        } else {
            _mood = Mood.ANGRY;
            _fireMoodEvent();
        }
    }

    //Will fire event of an specific type when called
    public synchronized void receiveHug() {
        if (_mood == Mood.ANGRY) {
            _mood = Mood.ANNOYED;
            _fireMoodEvent();
        } else {
            _mood = Mood.HAPPY;
            _fireMoodEvent();
        }
    }

    //Register events
    public synchronized void addMoodListener(MoodListener l) {
        _listeners.add(l);
    }

    //Remove registered events
    public synchronized void removeMoodListener(MoodListener l) {
        _listeners.remove(l);
    }

    //Fire event
    private synchronized void _fireMoodEvent() {
        MoodEvent mood = new MoodEvent(this, _mood);
        Iterator listeners = _listeners.iterator();
        while (listeners.hasNext()) {
            //Receive the event
            ((MoodListener) listeners.next()).moodReceived(mood);
        }
    }
}