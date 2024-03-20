# java-chess

# 기능 요구 사항

### 체스말/기물 (Piece)

- [x] King, Queen, Rook, Knight, Bishop, Pawn 등 여섯 종류의 말이 있다.
- [x] Color 를 가진다.
- [ ] 기물끼리는 특수한 경우를 제외하고 뛰어넘을 수 없다.
- [ ] 움직이는 기물과 다른 색을 가진 기물이 목적지에 존재한다면, 기존 기물은 사라진다. (Capture)

#### 방향

나이트를 제외한 기물이 움직이는 방향을 결정한다.

- [x] 두 점이 주어졌을 때, 8방향 중 어느 방향인지 결정한다.

#### 킹

- [ ] 상하좌우, 대각선 한 칸씩 이동할 수 있다.
- [ ] (특수 룰: 캐슬링)

#### 퀸

- [ ] 상하좌우, 대각선 원하는 만큼 이동할 수 있다.

#### 룩

- [ ] 상하좌우 원하는 만큼 이동할 수 있다.
- [ ] (특수 룰: 캐슬링)

#### 비숍

- [ ] 대각선 원하는 만큼 이동할 수 있다.

#### 나이트

- [ ] 상하좌우 두 칸 전진한 뒤, 전진한 방향의 90도 좌/우 한 칸으로 이동할 수 있다.
- [ ] 나이트는 다른 말을 뛰어넘을 수 있다.

#### 폰

- [ ] 앞으로 한 칸 전진할 수 있다.
- [ ] 앞쪽 대각선에 상대의 기물이 존재하는 경우, 상대방의 기물을 잡으면서 그 칸으로 이동할 수 있다.
- [ ] 한 번도 움직이지 않았다면, 두 칸 전진할 수 있다.
- [ ] 후진할 수 없다.
- [ ] (특수 룰: 앙파상, 프로모션)

### 색 (Color)

- [x] WHITE, BLACK 두 가지의 색을 가진다.

### 위치 (Position)

- [x] File, Rank 로 이루어져 있다.
- [x] 다른 위치를 받아 해당 위치와 같은 행/열, 대각 방향 또는 나이트 경로에 있는지 판단한다.

### File (체스 판의 열)

- [x] a ~ h 까지 총 8개의 열이 있다.

### Rank (체스 판의 행)

- [x] 1 ~ 8 까지 총 8개의 행이 있다.

### 판 (Board)

- [x] Position 과 Piece 를 매핑한다.

### BoardInitializer

- [x] 최초 판에 말을 놓은 Board 를 만든다.
