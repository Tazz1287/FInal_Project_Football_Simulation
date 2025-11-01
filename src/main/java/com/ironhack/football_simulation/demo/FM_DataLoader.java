package com.ironhack.football_simulation.demo;

import com.ironhack.football_simulation.enums.CoachPosition;
import com.ironhack.football_simulation.enums.Position;
import com.ironhack.football_simulation.model.Club;
import com.ironhack.football_simulation.model.Coach;
import com.ironhack.football_simulation.model.Player;
import com.ironhack.football_simulation.repository.ClubRepository;
import com.ironhack.football_simulation.repository.CoachRepository;
import com.ironhack.football_simulation.repository.PlayerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FM_DataLoader implements CommandLineRunner {

    private ClubRepository clubRepository;
    private PlayerRepository playerRepository;
    private CoachRepository coachRepository;

    public FM_DataLoader(ClubRepository clubRepository, PlayerRepository playerRepository, CoachRepository coachRepository) {
        this.clubRepository = clubRepository;
        this.playerRepository = playerRepository;
        this.coachRepository = coachRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (clubRepository.count() == 0) {
            System.out.println("Loading Football Simulation Data...");


            Club manUnited = new Club("Manchester United");
            Club realMadrid = new Club("Real Madrid");
            Club barcelona = new Club("Barcelona");
            Club bayern = new Club("Bayern");

            clubRepository.saveAll(List.of(manUnited, realMadrid, barcelona, bayern));

            //Manchester United Players

            Player manU1 = new Player("Senne Lammens", Position.GOALKEEPER, 31, 82);
            Player manU2 = new Player("Altay Bayindir", Position.GOALKEEPER, 1, 75);
            Player manU3 = new Player("Matthijs DeLigt", Position.DEFENDER, 4, 87);
            Player manU4 = new Player("Lisandro Martinez", Position.DEFENDER, 6, 85);
            Player manU5 = new Player("Harry Maguire", Position.DEFENDER, 5, 80);
            Player manU6 = new Player("Diogo Dalot", Position.DEFENDER, 2, 82);
            Player manU7 = new Player("Luke Shaw", Position.DEFENDER, 2, 79);
            Player manU8 = new Player("Bruno Fernandes", Position.MIDFIELD, 8, 88);
            Player manU9 = new Player("Miguel Ugarte", Position.MIDFIELD, 25, 85);
            Player manU10 = new Player("Casemiro", Position.MIDFIELD, 18, 83);
            Player manU11 = new Player("Kobbie Mainoo", Position.MIDFIELD, 7, 84);
            Player manU12 = new Player("Mason Mount", Position.MIDFIELD, 7, 82);
            Player manU13 = new Player("Benjamin Sesko", Position.FORWARD, 30, 84);
            Player manU14 = new Player("Matheus Cunha", Position.FORWARD, 10, 83);
            Player manU15 = new Player("Amad Diallo", Position.FORWARD, 16, 79);

            manU1.setClub(manUnited);
            manU2.setClub(manUnited);
            manU3.setClub(manUnited);
            manU4.setClub(manUnited);
            manU5.setClub(manUnited);
            manU6.setClub(manUnited);
            manU7.setClub(manUnited);
            manU8.setClub(manUnited);
            manU9.setClub(manUnited);
            manU10.setClub(manUnited);
            manU11.setClub(manUnited);
            manU12.setClub(manUnited);
            manU13.setClub(manUnited);
            manU14.setClub(manUnited);
            manU15.setClub(manUnited);

            playerRepository.saveAll(List.of(manU1, manU2, manU3, manU4, manU5, manU6, manU7, manU8, manU9, manU10, manU11, manU12, manU13, manU14, manU15));


            //Real Madrid Players

            Player rm1 = new Player("Thibaut Courtois", Position.GOALKEEPER, 1, 90);
            Player rm2 = new Player("Andriy Lunin", Position.GOALKEEPER, 13, 83);
            Player rm3 = new Player("Trent Alexandre-Arnold", Position.DEFENDER, 12, 88);
            Player rm4 = new Player("Elder Militao", Position.DEFENDER, 12, 88);
            Player rm5 = new Player("David Alaba", Position.DEFENDER, 3, 84);
            Player rm6 = new Player("Antonio Rudiguer", Position.DEFENDER, 4, 87);
            Player rm7 = new Player("Dean Huijsen", Position.DEFENDER, 24, 82);
            Player rm8 = new Player("Jude Bellingham", Position.MIDFIELD, 5, 91);
            Player rm9 = new Player("Federico Valverde", Position.MIDFIELD, 8, 89);
            Player rm10 = new Player("Aurélien Tchouaméni", Position.MIDFIELD, 14, 87);
            Player rm11 = new Player("Eduardo Camavinga", Position.MIDFIELD, 6, 86);
            Player rm12 = new Player("Arda Güler", Position.MIDFIELD, 15, 84);
            Player rm13 = new Player("Kylian Mbappé", Position.FORWARD, 10, 93);
            Player rm14 = new Player("Vinícius Júnior", Position.FORWARD, 7, 92);
            Player rm15 = new Player("Endrick", Position.FORWARD, 9, 83);

            rm1.setClub(realMadrid);
            rm2.setClub(realMadrid);
            rm3.setClub(realMadrid);
            rm4.setClub(realMadrid);
            rm5.setClub(realMadrid);
            rm6.setClub(realMadrid);
            rm7.setClub(realMadrid);
            rm8.setClub(realMadrid);
            rm9.setClub(realMadrid);
            rm10.setClub(realMadrid);
            rm11.setClub(realMadrid);
            rm12.setClub(realMadrid);
            rm13.setClub(realMadrid);
            rm14.setClub(realMadrid);
            rm15.setClub(realMadrid);

            playerRepository.saveAll(List.of(rm1, rm2, rm3, rm4, rm5, rm6, rm7, rm8, rm9, rm10, rm11, rm12, rm13, rm14, rm15));


            //Bayer Players

            Player bm1 = new Player("Manuel Neuer", Position.GOALKEEPER, 1, 90);
            Player bm2 = new Player("Jonas Urbig", Position.GOALKEEPER, 40,77);
            Player bm3 = new Player("Jonathan Tah", Position.GOALKEEPER, 4,85);
            Player bm4 = new  Player("Kim Min-Jae", Position.DEFENDER,3,86);
            Player bm5 = new Player ("Dayot Upamecano",Position.DEFENDER,2,84);
            Player bm6 = new Player("Alphonso Davies", Position.DEFENDER, 19, 87);
            Player bm7 = new Player("Josip Stanišić", Position.DEFENDER, 44, 81);
            Player bm8 = new Player("Joshua Kimmich", Position.MIDFIELD, 6, 89);
            Player bm9= new Player("Jamal Musiala", Position.MIDFIELD, 10, 88);
            Player bm10 = new Player("Leon Goretzka", Position.MIDFIELD, 8, 84);
            Player bm11 = new Player("Konrad Laimer", Position.MIDFIELD, 27, 83);
            Player bm12 = new Player("Aleksandar Pavlović", Position.MIDFIELD, 45, 82);
            Player bm13 = new Player("Harry Kane", Position.FORWARD, 9, 90);
            Player bm14 = new Player("Luis Díaz", Position.FORWARD, 14, 87);
            Player bm15 = new Player("Michael Olise", Position.FORWARD, 17, 85);

            bm1.setClub(bayern);
            bm2.setClub(bayern);
            bm3.setClub(bayern);
            bm4.setClub(bayern);
            bm5.setClub(bayern);
            bm6.setClub(bayern);
            bm7.setClub(bayern);
            bm8.setClub(bayern);
            bm9.setClub(bayern);
            bm10.setClub(bayern);
            bm11.setClub(bayern);
            bm12.setClub(bayern);
            bm13.setClub(bayern);
            bm14.setClub(bayern);
            bm15.setClub(bayern);

            playerRepository.saveAll(List.of(bm1,bm2,bm3,bm4,bm5,bm6,bm7,bm8,bm9,bm10,bm11,bm12,bm13,bm14,bm15));

            //FC Barcelona Players

            Player bc1 = new Player ("Marc Andre Ter-Stegen", Position.GOALKEEPER, 1, 90);
            Player bc2 = new Player("Joan García", Position.GOALKEEPER, 13, 79);
            Player bc3 = new Player("Ronald Araújo", Position.DEFENDER, 4, 88);
            Player bc4 = new Player("Jules Koundé", Position.DEFENDER, 23, 86);
            Player bc5 = new Player("Pau Cubarsí", Position.DEFENDER, 5, 84);
            Player bc6 = new Player("Alejandro Balde", Position.DEFENDER, 3, 85);
            Player bc7 = new Player("Andreas Christensen", Position.DEFENDER, 15, 83);
            Player bc8 = new Player("Pedri", Position.MIDFIELD, 8, 88);
            Player bc9 = new Player("Gavi", Position.MIDFIELD, 6, 86);
            Player bc10 = new Player("Frenkie de Jong", Position.MIDFIELD, 21, 87);
            Player bc11 = new Player("Dani Olmo", Position.MIDFIELD, 20, 85);
            Player bc12 = new Player("Fermín López", Position.MIDFIELD, 16, 82);
            Player bc13 = new Player("Robert Lewandowski", Position.FORWARD, 9, 87);
            Player bc14 = new Player("Lamine Yamal", Position.FORWARD, 10, 86);
            Player bc15 = new Player("Marcus Rashford", Position.FORWARD, 14, 85);

            bc1.setClub(barcelona);
            bc2.setClub(barcelona);
            bc3.setClub(barcelona);
            bc4.setClub(barcelona);
            bc5.setClub(barcelona);
            bc6.setClub(barcelona);
            bc7.setClub(barcelona);
            bc8.setClub(barcelona);
            bc9.setClub(barcelona);
            bc10.setClub(barcelona);
            bc11.setClub(barcelona);
            bc12.setClub(barcelona);
            bc13.setClub(barcelona);
            bc14.setClub(barcelona);
            bc15.setClub(barcelona);

            playerRepository.saveAll(List.of(bc1,bc2,bc3,bc4,bc5,bc6,bc7,bc8,bc9,bc10,bc11,bc12,bc13,bc14,bc15));

            // Manchester United Coaches
            Coach manUC1 = new Coach("Erik ten Hag", CoachPosition.HEAD_COACH);
            Coach manUC2 = new Coach("Steve McClaren", CoachPosition.ASSISTANT_COACH);
            Coach manUC3 = new Coach("Richard Hartis", CoachPosition.GOALKEEPER_COACH);

            manUC1.setClub(manUnited);
            manUC2.setClub(manUnited);
            manUC3.setClub(manUnited);

            coachRepository.saveAll(List.of(manUC1, manUC2, manUC3));

            // Real Madrid Coaches
            Coach rmC1 = new Coach("Carlo Ancelotti", CoachPosition.HEAD_COACH);
            Coach rmC2 = new Coach("Davide Ancelotti", CoachPosition.ASSISTANT_COACH);
            Coach rmC3 = new Coach("Luis Llopis", CoachPosition.GOALKEEPER_COACH);

            rmC1.setClub(realMadrid);
            rmC2.setClub(realMadrid);
            rmC3.setClub(realMadrid);

            coachRepository.saveAll(List.of(rmC1, rmC2, rmC3));


            // Bayern Munich Coaches
            Coach bnC1 = new Coach("Thomas Tuchel", CoachPosition.HEAD_COACH);
            Coach bnC2 = new Coach("Zsolt Löw", CoachPosition.ASSISTANT_COACH);
            Coach bnC3 = new Coach("Michael Rechner", CoachPosition.GOALKEEPER_COACH);

            bnC1.setClub(bayern);
            bnC2.setClub(bayern);
            bnC3.setClub(bayern);

            coachRepository.saveAll(List.of(bnC1, bnC2, bnC3));


            // Barcelona Coaches
            Coach bcC1 = new Coach("Xavi Hernández", CoachPosition.HEAD_COACH);
            Coach bcC2 = new Coach("Óscar Hernández", CoachPosition.ASSISTANT_COACH);
            Coach bcC3 = new Coach("José Ramón de la Fuente", CoachPosition.GOALKEEPER_COACH);

            bcC1.setClub(barcelona);
            bcC2.setClub(barcelona);
            bcC3.setClub(barcelona);

            coachRepository.saveAll(List.of(bcC1, bcC2, bcC3));


            System.out.println("Database loaded");
        }else{
            System.out.println("Database already loaded");

        }
    }
}





