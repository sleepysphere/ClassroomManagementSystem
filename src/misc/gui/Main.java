package gui;

import javax.swing.SwingUtilities;
import repository.ClassRepository;
import repository.RoomRepository;
import repository.ScheduleRepository;


public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
 
            ClassRepository classRepository = null; //Inject real implementation
            RoomRepository roomRepository = null; // Inject real implementation
            ScheduleRepository scheduleRepository = null; //Inject real implementation
            
            MainFrame mainFrame = new MainFrame(classRepository, roomRepository, scheduleRepository);
            mainFrame.setVisible(true);
        });
    }
}
