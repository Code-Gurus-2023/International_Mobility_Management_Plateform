import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccomodationOffersComponent } from './accomodation-offers.component';

describe('AccomodationOffersComponent', () => {
  let component: AccomodationOffersComponent;
  let fixture: ComponentFixture<AccomodationOffersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AccomodationOffersComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AccomodationOffersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
