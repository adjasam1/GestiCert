import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagementCertificateComponent } from './management-certificate.component';

describe('ManagementCertificateComponent', () => {
  let component: ManagementCertificateComponent;
  let fixture: ComponentFixture<ManagementCertificateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManagementCertificateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManagementCertificateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
