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
    { id: 2, name: "Ivysaur", type: "grass", icon: "kieken.jpg" },
    { id: 3, name: "Venusaur", type: "grass", icon: "kieken.jpg" },
    { id: 4, name: "Charmander", type: "fire", icon: "kieken.jpg" },
    { id: 5, name: "Charmeleon", type: "fire", icon: "kieken.jpg" },
    { id: 6, name: "Charizard", type: "fire", icon: "kieken.jpg" },
    { id: 7, name: "Squirtle", type: "water", icon: "kieken.jpg" },
    { id: 8, name: "Wartortle", type: "water", icon: "kieken.jpg" },
    { id: 9, name: "Blastoise", type: "water", icon: "kieken.jpg" }
  ];
}
