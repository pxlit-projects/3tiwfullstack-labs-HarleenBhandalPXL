import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {NewsletterComponent} from "./newsletter/newsletter.component";
import {LogoComponent} from "./logo/logo.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NewsletterComponent, LogoComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'chapter3-exercise1';
}
