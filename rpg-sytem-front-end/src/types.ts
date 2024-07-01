export type RpgConfigs = {
  scenario: Scenario;
  music: Music;
  enemies: Enemy[];
};

export type Scenario = {
  id: number;
  name: string;
  image: string;
};

export type Music = {
  id: number;
  name: string;
};

export type Enemy = {
  id: number;
  name: string;
  image: string;
};
