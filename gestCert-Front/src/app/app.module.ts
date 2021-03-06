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
import {LayoutModule} from '@angular/cdk/layout';
import {DevGuard} from './jwt-security/guards/dev.guard';
import {AdminGuard} from './jwt-security/guards/admin.guard';
import {JwtInterceptor} from './jwt-security/http-interceptor/jwt.interceptor';
import {HomeComponent} from './web-pages/home/home.component';
import {ProfileComponent} from './web-pages/profile/profile.component';
import {DialogModule} from 'primeng/dialog';
import {ManagementComponent} from './web-pages/administration/management/management.component';
import {ManagementProfileComponent} from './web-pages/administration/management-profile/management-profile.component';
import {ManagementDepartmentComponent} from './web-pages/administration/management-department/management-department.component';
import { ManagementUserComponent } from './web-pages/administration/management-user/management-user.component';
import { ManagementEnvironmentComponent } from './web-pages/administration/management-environment/management-environment.component';
import { ManagementCertificateComponent } from './web-pages/administration/management-certificate/management-certificate.component';
import { ManagementApplicationComponent } from './web-pages/administration/management-application/management-application.component';
import {TableModule} from 'primeng/table';
import {AutoCompleteModule, MessageModule, MessagesModule, MultiSelectModule, PickListModule} from 'primeng/primeng';
import { ManagementPlateformComponent } from './web-pages/administration/management-plateform/management-plateform.component';
import { ManagementRootComponent } from './web-pages/administration/management-root/management-root.component';
import { ManagementServerComponent } from './web-pages/administration/management-server/management-server.component';
import {ManagementHabilitationComponent} from './web-pages/administration/management-habilitation/management-habilitation.component';
import { NewDemandComponent } from './web-pages/demandCertificate/new-demand/new-demand.component';
import {DemandComponent} from './web-pages/demandCertificate/demand/demand.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    ContactComponent,
    SiteComponent,
    AccessibiliteComponent,
    HomeComponent,
    ProfileComponent,
    CertificateComponent,
    LoginComponent,
    ManagementComponent,
    DemandComponent,
    ManagementProfileComponent,
    ManagementDepartmentComponent,
    ManagementUserComponent,
    ManagementApplicationComponent,
    ManagementEnvironmentComponent,
    ManagementCertificateComponent,
    ManagementPlateformComponent,
    ManagementRootComponent,
    ManagementServerComponent,
    ManagementHabilitationComponent,
    NewDemandComponent,
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
    ReactiveFormsModule,
    DialogModule,
    TableModule,
    MessagesModule,
    MessageModule,
    AutoCompleteModule,
    PickListModule,
    MultiSelectModule
  ],
  providers: [DevGuard, AdminGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true
    }],
  bootstrap: [AppComponent]
})
export class AppModule { }
