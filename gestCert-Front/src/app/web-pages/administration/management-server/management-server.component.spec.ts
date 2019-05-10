import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagementServerComponent } from './management-server.component';

describe('ManagementServerComponent', () => {
  let component: ManagementServerComponent;
  let fixture: ComponentFixture<ManagementServerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManagementServerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManagementServerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
