package ca.umontreal.IFT2015.introduction.muzik;

/**
Created by François Major on 2022.09.10

Permission is hereby granted, free of charge, to any person obtaining a copy of this Software and
associated documentation files, to deal in the Software without restriction, including without
limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The following copyright notice and this permission notice shall be included in all copies or
substantial portions of the Software: “MajorLab Software: Copyright 1994-2022 Université de
Montréal, François Major’s Laboratory”.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

/**
* Genre is a class to manage musical genres
* 
* @author      Francois Major
* @version     1.1, 2024.09.05 convert iTunes genre "Francais" (with cedille) to French, added genres in alphabetical order with sub-genres
* @since       1.0, 2022.09.10
*/

public class Genre {

    /**
     * aGenre is the enum that lists the musical genres
     * 
     * @author      Francois Major
     * @version     1.1
     * @since       1.0, 2022.09.10
     */

    public enum aGenre {
	// A
	Acoustic, Ambient,
	African, AfroBeat,

	// B
	Bass, BassLine,
	Blues, ChicagoBlues, ElectricBlues,
	Brazilian, BrazilianPop, Sertanejo, Samba, BossaNova,
	Breaks, BreakBeat,

	// C
	Canadian, Children,
	Christmas, Hollyday,
	Classical,
	Comedy,

	// D
	Dance, Club, Disco, Dubstep, NuDisco, HardDance, Indie, IndieDance,
	DrumNBass, DownTempo,

	// E
	Electro, ElectroPop, Electronic, Electronica,

	// F
	FitnessWorkout,
	Folk, BlueGrass, Country, Fado,
	French, FrenchPop, Francophone, Quebecois,

	// G
	Garage, UKGarage,
	Gothic, GothicRock,

	// H
	House, AfroHouse, BassHouse, DeepHouse, FrenchHouse, FunkyHouse, FutureHouse, JackinHouse, TechHouse,

	// I
	Inspirational,

	// J
	Jazz, AcidJazz,

	// L
	Latino, Salsa,
	Lounge, MelodicHouse, OrganicHouse, ProgressiveHouse, Amapiano,

	// M
	MainStage,

	// N
	NewArtist,

	// O
	Other,

	// P
	Pop, KPop, HipHop, Rap,
	Punk, NewWave,

	// R
	RNB, Soul, Funk,
	Reggae, Reggaeton,

	// Rock
	Rock, Alternative, ClassicRock, Grunge, Metal, RockNRoll, DooWop,

	// S
	Singer, SongWriter,
	Slow, Smooth,
	Soundtrack,

	// T
	Techno, HardCore, HardTechno, Industrial,
	Trance, PsyTrance, Tribal,
	Trap, KBass, Wave,

	// W
	Worldwide,

	// Z
	Zydeco, Cajun
    }

    private aGenre genre = aGenre.Other; // attribute with default value Other.

    /**
     * Returns the Genre corresponding to a String.
     * if the String does not match any Genre, then the Genre Other is returned.
     *
     * @param genre String possibly matching one of the Genre
     * @return      Genre
     * @see         Track
     */
    private aGenre toGenre( String genre ) {
	for( aGenre g : aGenre.values() )
	    if( genre.contains( g.name() ) ) return g;
	return aGenre.Other;
    }

    /**
     * Build a Genre, constructor from String
     */
    public Genre( String g ) {
	if( g.equals( "Français" ) ) g = "French";
	this.genre = toGenre( g );
    }

    /**
     * Returns a String for this Genre to be human readable
     *
     * @return  a String with the info of this Genre
     * @see     Genre
    */
    @Override
    public String toString() {
	return this.genre.name();
    }
}
