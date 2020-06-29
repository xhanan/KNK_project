package sample.Components

public class RoomDetails {
   public Node singleComponent(Rooms room, long daysToStay, EventHandler<ActionEvent> handler) throws Exception{
      FXMLLoader loader=new FXMLLoader();
      URL url=new File("src/views/room-details.fxml").toURI().toURL();
      loader.setLocation(url);
      
   }
}
