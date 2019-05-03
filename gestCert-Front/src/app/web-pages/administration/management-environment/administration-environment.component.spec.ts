import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdministrationEnvironmentComponent } from './administration-environment.component';

describe('AdministrationEnvironmentComponent', () => {
  let component: AdministrationEnvironmentComponent;
  let fixture: ComponentFixture<AdministrationEnvironmentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdministrationEnvironmentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdministrationEnvironmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
