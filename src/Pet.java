public abstract class Pet {
    public String name;
    public int hp, maxHp, aura, rizz;
    public int x, y;
    public int frameIndex = 0;
    public int state = 0;
    public BufferedImage spriteSheet;

    // Projectile logic
    public boolean projectileActive = false;
    public int projX, projY;

    public Pet(String name, int hp, int aura, int rizz, String spritePath, int x, int y) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.aura = aura;
        this.rizz = rizz;
        this.x = x;
        this.y = y;
        // ... (existing image loading)
    }

    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    // AI logic: Move toward a target Pet
    public void chase(Pet target) {
        if (this.x < target.x) this.x += 2;
        if (this.x > target.x) this.x -= 2;
        if (this.y < target.y) this.y += 2;
        if (this.y > target.y) this.y -= 2;
    }

    public double getDistanceTo(Pet other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    public void takeDamage(int damage) {
        this.hp = Math.max(0, this.hp - damage);
    }

    // ... (existing getCurrentFrame logic)
}