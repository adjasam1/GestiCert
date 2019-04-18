import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HeaderComponent} from './header-footer/header/header.component';
import {FooterComponent} from './header-footer/footer/footer.component';
import {ContactComponent} from './header-footer/contact/contact.component';
import {SiteComponent} from './header-footer/site/site.component';
import {AccessibiliteComponent} from './header-footer/accessibilite/accessibilite.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {
  MatButtonModule,
  MatButtonToggleModule,
  MatCardModule,
  MatDialogModule, MatGridListModule,
  MatIconModule,
  MatInputModule, MatListModule,
  MatMenuModule,
  MatProgressSpinnerModule, MatRadioModule, MatSelectModule, MatSidenavModule,
  MatTableModule,
  MatToolbarModule
} from '@angular/material';
import {CommonModule} from '@angular/common';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {CertificateComponent} from './web-pages/certificate/certificate.component';
import {LoginComponent} from './web-pages/login/login.component';
import {ManagementComponent} from './web-pages/management/management.component';
import {LayoutModule} from '@angular/cdk/layout';
import {DevGuard} from './jwt-security/guards/dev.guard';
import {ServiceGuard} from './jwt-security/guards/service.guard';
import {AdminGuard} from './jwt-security/guards/admin.guard';
import {JwtInterceptor} from './jwt-security/http-interceptor/jwt.interceptor';
import {AuthenticationComponent} from './web-pages/authentication/authentication.component';
import {HomeComponent} from './web-pages/home/home.component';
import {ProfileComponent} from './web-pages/profile/profile.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    AuthenticationComponent,
    ContactComponent,
    SiteComponent,
    AccessibiliteComponent,
    HomeComponent,
    ProfileComponent,
    CertificateComponent,
    LoginComponent,
    ManagementComponent
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
    MatButtonToggleModule,
    BrowserAnimationsModule,
    MatGridListModule,
    LayoutModule,
    MatSidenavModule,
    MatListModule,
    MatSelectModule,
    MatRadioModule,
    ReactiveFormsModule
  ],
  providers: [DevGuard, ServiceGuard, AdminGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true
    }],
  bootstrap: [AppComponent]
})
export class AppModule { }