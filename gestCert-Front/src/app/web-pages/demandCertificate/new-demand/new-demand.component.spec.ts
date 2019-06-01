import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewDemandComponent } from './new-demand.component';

describe('NewDemandComponent', () => {
  let component: NewDemandComponent;
  let fixture: ComponentFixture<NewDemandComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewDemandComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewDemandComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
