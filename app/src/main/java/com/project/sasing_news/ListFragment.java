package com.project.sasing_news;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

public class ListFragment extends Fragment {

    private OnHeadlineSelectedListener callback;

    // Container Activity must implement this interface
    public interface OnHeadlineSelectedListener {
        void onArticleSelected(String headline, String content);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (OnHeadlineSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        ListView listView = view.findViewById(R.id.headlineListView);

        // Define headlines and contents
        String[] headlines = {
                "Gaimin Gladiators: \"Our Dota 2 roster has reached the end of an era\"",
                "Boom Esports return to Southeast Asia with Filipino legends DJ and Tims",
                "Team Liquid crowned as The International 2024 champions with sweep over rivals Gaimin Gladiators",
                "Crownfall Act IV introduces Kez as new playable hero",
                "Yatoro and Mira take a competitive break, Team Spirit down to 3 players",
                "Ramzes will not continue with Tundra Esports"
        };

        String[] contents = {
                "Gaimin Gladiators posted an announcement that many fans took as a promise of a roster change. The organization said, that its Dota 2 roster \"has reached an end of an era\".\n\n"
                        + "Gaimin Gladiators played with the same roster for almost two years after signing Quinn \"Quinn\" Callahan at the end of 2022. They were the most dominant team in 2023, winning all three Majors and reaching The International 2023 finals. In 2024, their results became worse, but closer to the end of the season they figured something out, winning Riyadh Masters 2024. They also reached The International 2024 finals, but were destroyed with a 3:0 score again.\n\n"
                        + "There are rumors about changes in Gaimin Gladiators already. The main one is that Alimzhan \"watson\" Islambekov will replace Anton \"dyrachyo\" Shkredov as a carry.",
                "Boom Esports ended their affair in South America after an underwhelming season and returned home to Southeast Asia. Boom ventured to South America in the post-TI12 shuffle when they signed a mix of stars and up-and-coming players from the region. But despite SA being regarded as one of the least stacked regions, Boom still struggled to stay at the top throughout the season and ended up missing out on a ticket to The International 2024 as well. In the post TI13 shuffle, the SEA organization dropped the entire team and are now the first to confirm a full new roster for the new season. However, two of the new players are joining on a trial basis.\n" +
                        "\n" +
                        "Former Xtreme Gaming and more recently, Nigma Galaxy carry player Daniel \"Ghost\" Chan along with Mc \"Mac\" Villanueva are joining the roster as trials and will prove their worth through the upcoming BetBoom Dacha Belgrade 2024 regional qualifiers.\n" +
                        "\n" +
                        "Along with Ghost, Boom have also recruited Saieful \"Fbz\" Ilham from Nigma, but the latter joins straight as a full member. The same applies for Timothy John \"TIMS\" Randrup, who comes from the recently disbanded Blacklist Rivalry roster and who will be taking the captainship role at Boom.\n" +
                        "\n" +
                        "Philippine legend Djardel \"DJ\" Mampusti is rounding up the roster at Boom Esports after having to wait for an opportunity since May, when he departed from Bleed Esports.\n" +
                        "\n" +
                        "Boom’s new line-up will have SEA veteran Chai \"Mushi\" Yee Fung joining in the coaching seat for the new season.",
                "After falling to Gaimin Gladiators in five previous grand finals, Team Liquid defeated their rivals in the one that mattered the most. Team Liquid have been crowned as this year’s Dota 2 world champions after they triumphed over rivals Gaimin Gladiators, 3-0, in an all-Western Europe showdown in The International 2024 (TI 2024) grand finals at the Royal Arena of Copenhagen, Denmark on Sunday (September 15). This was the most dominant performance by a TI champion ever, as Liquid won every single game they played in the Playoffs and their only losses came during the preliminary Group Stage and Seeding Decider phases. This also marks the third-straight year that the TI grand finals ended in a 3-0 sweep.\n" +
                        "\n" +
                        "With their victory, Liquid claimed the grand prize of $1.15 million out of the tournament’s $2.56 million prize pool as well as the organisation's second Aegis of Champions to add to their first from TI 2017.git.",
                "The release of Crownfall Act IV will also bring Kez into action as the newest hero. Shortly before the Dota 2 Grand Finals at The International 2024, Valve released the newest hero that will come along with CrownFall ACT IV.\n" +
                        "Introducing Kez: head of the Kazurai order, leader of the flightless, recruited to help in the fight against Queen Imperia. According to the story, Kez lost his will to fight after the failure of the Kazurai army recruitment in Icewrack, but after defeating the dragon Zaug, he is willing to fight again against Imperia. However, he is not quite in agreement on Shendel becoming the \"new queen\", for now...\n" +
                        "\n" +
                        "Although not much information has been provided about Kez, one can guess he will be a core, melee hero. \n" +
                        "\n" +
                        "Crownfall has been unfolding over the last year. The first act saw Vengeful Spirit and Skywrath Mage search for Scree'Auk, the second act saw Skywrath travel to the Deserts of Druud, and the third act, released in July, was Vengeful Spirit's Journey to Icewrack.",
                "The post-TI13 shuffle hits the fans hard as the two-time TI champions of Team Spirit Yatoro/Raddan and Mira take a competitive break. According to the official announcement made by Team Spirit on Wednesday, September, 18, both Illya \"Yatoro\" Mulyarchuk and Myroslav \"Mira\" Kolpakov become inactive and will take a break from their professional careers at the players’ request. At the end of four years with Team Spirit, Mira and Yatoro, who ahead of this year’s TI, changed his nickname to Raddan, head into an indefinite competitive break with their names engraved on the Aegis of Champions twice, a Riyadh Mastery trophy, a Major trophy and multiple grand finals appearances and a handful of other third party championship titles.  \n" +
                        "\n" +
                        "In the meantime, Team Spirit are among the directly invited teams for the upcoming two LAN tournaments, PGL Wallachia Season 2 and BetBoom Dacha Belgrade 2024, which means that the fans won’t have to wait for too long before seeing the new Team Spirit roster.",
                "On loan for part of the year, Roman \"RAMZES666\" Kushnarev will not stay with Tundra for the upcoming season. Roman \"RAMZES666\" Kushnarev joined up with Tundra Esports when he replaced Tobias \"Tobi\" Buchner who replaced Ivan \"MinD_ContRoL\" Ivanov four months ago. He came from L1ga Team on a loan deal for the remainder of the season. With him on board Tundra reached the top 4 at Riyadh Masters 2024 and placed third at the recently concluded FISSURE Universe: Episode 3 after losing to BetBoom Team and Falcons. They qualified for The International 2024 where they took an impressive third-place finish. "

        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.headlineTextView, headlines);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            String selectedHeadline = headlines[position];
            String selectedContent = contents[position];
            callback.onArticleSelected(selectedHeadline, selectedContent);
        });

        return view;
    }
}



