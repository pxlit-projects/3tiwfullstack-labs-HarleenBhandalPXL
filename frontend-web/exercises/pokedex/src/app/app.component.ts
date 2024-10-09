import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {Pokemon} from "./shared/models/pokemon-model";
import {PokemonItemComponent} from "./pokemon-item/pokemon-item.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, PokemonItemComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'pokedex';

  pokemons: Pokemon[] = [
    { id: 1, name: "Bulbasaur", type: "grass", icon: "1.png" },
    { id: 2, name: "Ivysaur", type: "grass", icon: "2.png" },
    { id: 3, name: "Venusaur", type: "grass", icon: "3.png" },
    { id: 4, name: "Charmander", type: "fire", icon: "4.png" },
    { id: 5, name: "Charmeleon", type: "fire", icon: "5.png" },
    { id: 6, name: "Charizard", type: "fire", icon: "6.png" },
    { id: 7, name: "Squirtle", type: "water", icon: "7.png" },
    { id: 8, name: "Wartortle", type: "water", icon: "8.png" },
    { id: 9, name: "Blastoise", type: "water", icon: "9.png" }
  ];
}
