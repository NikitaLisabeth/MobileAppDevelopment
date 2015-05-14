package be.howest.nmct.provider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import be.howest.nmct.Model.Theatre;

/**
 * Created by Nikita on 16/04/2015.
 */
public class TheatreProvider {
    private static TheatreProvider instance = new TheatreProvider();
    private static List<Theatre> theatresWestEnd;

    public static TheatreProvider getInstance(){
        return instance;
    }

    private TheatreProvider(){
        loadTheatres();
    }

    public static List<Theatre> getTheatresWestEnd() {
        return theatresWestEnd;
    }

    private void loadTheatres(){
        /*private String name;
        private String address;
        private String currentMusical;
        private String stageDoor;
        private String location;*/
        theatresWestEnd = new ArrayList<>();
        Theatre AldwychTheatre = new Theatre("Aldwych Theatre",
                " 49 Aldwych, London, WC2B 4DF","Beautiful",
                "You go out of the theatre. Go left into Dury Lane. At your left side you find the stagedoor.",
                "https://www.google.be/maps/place/Aldwych+Theatre/@51.512946,-0.118634,17z/data=!3m1!4b1!4m2!3m1!1s0x487604ca8ff4f43b:0xcee9dd4da02ea67e");
        theatresWestEnd.add(AldwychTheatre);
        Theatre PhoenixTheatre = new Theatre("Phoenix Theatre",
                "Charing Cross Road, London, WC2H 0JP",
                "Bend it like Beckham",
                "When you exit the theatre, you will come out of one of two entrances: the main one on Charing Cross Road, or the side entrance on Phoenix Street. Upon exiting on Charing Cross Road, turn left until you see Phoenix Street and the side entrance, and proceed until the end of the street.  At the end of the street, take another left into Stacey Street, the road on which the stage door itself is located, and walk until the very end of the street where you will come to a garage door. The stage door will be on your left.",
                "https://www.google.be/maps/place/Phoenix+Theatre/@51.51433,-0.129527,17z/data=!3m1!4b1!4m2!3m1!1s0x487604d2af4c52e1:0x929e47af1c8d7a4");
        theatresWestEnd.add(PhoenixTheatre);
        Theatre VictoriaPalaceTheatre = new Theatre("Victoria Palace Theatre",
                "Victoria Street, London, SW1E 5EA",
                "Billy Elliot",
                "",
                "https://www.google.be/maps/place/Victoria+Palace+Theatre/@51.496666,-0.142506,17z/data=!3m1!4b1!4m2!3m1!1s0x48760520f5769eeb:0xfe7a7316a2c24c8e");
        theatresWestEnd.add(VictoriaPalaceTheatre);
        Theatre PrinceOfWalesTheatre = new Theatre("Prince of Wales Theatre",
                "31 Coventry Street, London, W1D 6AS",
                "Book of Mormon",
                "Go outside the theatre. Go left in Oxendon Street. Go into the first street left (Whitcomb Street). At the end of the street at your left hand you can find the stagedoor",
                "https://www.google.be/maps/place/Prince+of+Wales+Theatre/@51.510212,-0.132024,17z/data=!3m1!4b1!4m2!3m1!1s0x487604d3cd2b4d57:0x8989ed2b7c5c37f8");
        theatresWestEnd.add(PrinceOfWalesTheatre);
        Theatre LondonPalladium = new Theatre("London Palladium",
                "8 Argyll Street, London, W1F 7TF",
                "Cats",
                "Turn left and walk down Argyll Street towards Liberty. When you reach Liberty, turn a left and walk down the street past the massive sign adversity the show currently running at the theatre, until you read the iron gates marked: ‘LONDON PALLADIUM’.",
                "https://www.google.be/maps/place/London+Palladium/@51.514526,-0.140576,19z/data=!3m1!4b1!4m2!3m1!1s0x0000000000000000:0xfc164c4b2e440089");
        theatresWestEnd.add(LondonPalladium);
        Theatre QueensTheatre = new Theatre("Queens Theatre",
                "51 Shaftesbury Avenue, London, W1D 6BA",
                "Les Misérables",
                "Go outside the theatre. Take the first street left (Wardour Street). The first street left is Winnett Street, here you can find the stagedoor",
                "https://www.google.be/maps/place/Queen's+Theatre/@51.511817,-0.132619,19z/data=!3m1!4b1!4m2!3m1!1s0x0000000000000000:0x97a51a83feb3ee7f");
        theatresWestEnd.add(QueensTheatre);
        Theatre MajestysTheatre = new Theatre("Majesty's Theatre",
                "57 Haymarket, London, SW1Y 4QL",
                "The Phantom of the Opera",
                "Go outside the theatre, go left (Charles II Street). Take the first street left, there you'll find the stagedoor ",
                "https://www.google.be/maps/place/Her+Majesty's+Theatre/@51.508276,-0.132321,19z/data=!4m2!3m1!1s0x487604d1a21e9f5b:0x24a8a42d999cafaf");
        theatresWestEnd.add(MajestysTheatre);
        Theatre ApolloVictoriaTheatre = new Theatre("Apollo Victoria Theatre",
                "17 Wilton Road, London, SW1V 1LG",
                "Wicked",
                "Turn left and walk about 100 yards.",
                "https://www.google.be/maps/place/Apollo+Victoria+Theatre/@51.49563,-0.142922,19z/data=!3m1!4b1!4m2!3m1!1s0x487605203f31d59b:0xfbe3b26f42e3485b");
        theatresWestEnd.add(ApolloVictoriaTheatre);
        Theatre PrinceEdwardTheatre = new Theatre("Prince Edward Theatre",
                "28 Old Compton Street, London, W1D 4HS",
                "Miss Saigon",
                "Leave the theatre, go to right. You take the first road left (Frith Street). At your right side you see the stagedoor.",
                "https://www.google.be/maps/place/Prince+Edward+Theatre/@51.513471,-0.130778,17z/data=!3m1!4b1!4m2!3m1!1s0x487604d2c198596b:0x1eb35ca6f094d27e");
        theatresWestEnd.add(PrinceEdwardTheatre);
        Theatre SavoyTheatre = new Theatre("Savoy Theatre",
                "Strand, London, WC2R 0ET",
                "Gypsy",
                "No matter where the theatre spits you out, always go right until you reach the black gates. ",
                "https://www.google.be/maps/place/Savoy+Theatre/@51.510116,-0.120538,17z/data=!3m1!4b1!4m2!3m1!1s0x487604c926ff263f:0x53ff9000a698bd76");
        theatresWestEnd.add(SavoyTheatre);
        Theatre PiccadillyTheatre = new Theatre("Piccadilly Theatre",
                "16 Denman Street, London, W1D 7DY",
                "Jersey Boys",
                "Go left, take the first street left (Denman Street). Go right until you see a black fence. That's the stagedoor.",
                "https://www.google.be/maps/place/Piccadilly+Theatre/@51.5108825,-0.1355742,19z/data=!4m2!3m1!1s0x487604d40c2fdf67:0xa14df20866754e24");
        theatresWestEnd.add(PiccadillyTheatre);

        Collections.sort(theatresWestEnd, new Comparator<Theatre>() {
            @Override
            public int compare(Theatre th1, Theatre th2) {
                return th1.getName().compareTo(th2.getName());
            }
        });
    }
}
