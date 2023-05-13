import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccomodationPagesComponent } from './accomodation-pages.component';

describe('AccomodationPagesComponent', () => {
  let component: AccomodationPagesComponent;
  let fixture: ComponentFixture<AccomodationPagesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AccomodationPagesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AccomodationPagesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
