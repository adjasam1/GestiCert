import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HeaderComponent} from './header-footer/header/header.component';
import {FooterComponent} from './header-footer/footer/footer.component';
import {AuthentificationComponent} from './authentication/authentification.component';
import {ContactComponent} from './header-footer/contact/contact.component';
import {SiteComponent} from './header-footer/site/site.component';
import {AccessibiliteComponent} from './header-footer/accessibilite/accessibilite.component';
import {AccueilComponent} from './home/accueil.component';
import {ProfileComponent} from './profile/profile.component';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {
  MatButtonModule,
  MatButtonToggleModule,
  MatCardModule,
  MatDialogModule, MatGridListModule,
  MatIconModule,
  MatInputModule,
  MatMenuModule,
  MatProgressSpinnerModule,
  MatTableModule,
  MatToolbarModule
} from '@angular/material';
import {CommonModule} from '@angular/common';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    AuthentificationComponent,
    ContactComponent,
    SiteComponent,
    AccessibiliteComponent,
    AccueilComponent,
    ProfileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    MatTableModule,
    CommonModule,
    MatToolbarModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatDialogModule,
    MatMenuModule,
    MatIconModule,
    MatProgressSpinnerModule,
    FormsModule,
    MatInputModule,
    MatButtonToggleModule,
    BrowserAnimationsModule,
    MatGridListModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
