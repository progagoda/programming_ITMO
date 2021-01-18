import interfaces.SewAction;
import person.*;
import enums.Places;
import exceptions.*;
public class Main {
    public static void main(String[] args) {
        Talks talks = new Talks("Talks", "Something");
        Neznayka neznayka = new Neznayka("Neznayka", "Human");
        Prisoners prisoners = new Prisoners("Prisoners", "Human");
        Room room = new Room("Room", "Something");
        Gear gear = new Gear("Gear", "Something");
        Difference difference = new Difference("Difference", "Something");
        Shorties shorties = new Shorties("Shorties", "Human");
        talks.setDescription("science");
        talks.setLength(80);
        talks.notBring();
        neznayka.setHand(15);
        neznayka.setCharacter("angry");
        neznayka.waved();
        neznayka.cameOut();
        prisoners.setTerm(15);
        prisoners.christened();
        room.setColor("prison");
        room.called(Places.OFFICE);
        room.reminded();
        gear.setDescription("Ship's");
        gear.stored();
        gear.called(Places.OFFICE);
        difference.setTopic("shelves");
        difference.setTime("past");
        difference.was();
        gear.noLying();
        shorties.setLocation("home");
        shorties.setCharacter("violent");
        shorties.lyingDown();
        System.out.println(prisoners.getAction());
        Bake bake = new Bake("Bake", "Something");
        Pipes pipes = new Pipes("Pipes", "Something");
        Anyone anyone = new Anyone("Anyone", "Human");
        AnotherShorties anotherShorties = new AnotherShorties("AnotherShorties", "Human");
        Someone someone = new Someone("Someone", "Human");
        Someone2 someone2 = new Someone2("Someone2", "Human");
        bake.setCastIron(true);
        bake.setColor("yellow");
        bake.Standing(Places.SLAMMER);
        pipes.setLength(21);
        pipes.setTinCans(true);
        pipes.Stretched();
        shorties.Bake();
        shorties.lyingDown();
        anyone.setShorties(true);
        anyone.setCharacter("funny");
        anyone.BeganToBlow();
        anyone.Throwing(15);
        anotherShorties.setShelves(true);
        anotherShorties.setWeight(3);
        anotherShorties.setFatigue(false);
        anotherShorties.Sat();
        someone.setVision(true);
        someone.setPerseverance(true);
        someone.setClothes(true);
        someone.Darning();
        SewAction sewAction = new SewAction() {
            private String Action;

            public boolean isVision() {
                boolean vision = true;
                return vision;
            }
            public String getAction() {
                return Action;
            }
            public void setAction(String action) {
                Action = action;
            }
            @Override
            public void Darning() {
                if (isVision()) {
                    setAction("I'm anonymous class and I darning");
                    System.out.println(getAction());
                }
            }
        };
        sewAction.Darning();
        someone2.setGame(true);
        someone2.Played("Rashibalochka");
        System.out.println(someone2.getAction());
        Neznayka.Hat hat= new Neznayka.Hat();
        hat.removed(true);
        System.out.println(hat.getAction());
        someone2.setHistoryLive(false);//делаем ошибку для History Exception
        someone2.Told("sad");
        pipes.setLength(-3);
        pipes.Stretched();
        someone.setVision(false);
        someone.Darning();
        }

       }

