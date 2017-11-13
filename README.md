# beepp2bee
BEE++ to BEE translator

It compiles some complex constraints into a bunch of primitive BEE constraints.

---

### Sketch of BEE++ syntax

#### Variable declaration

* Integer (order) variable: `int a: <domain>`
* Integer (dual) variable: `dual_int a: <domain>`
* Boolean variable: `bool a`

where `<domain>` is a union of ranges or a single range: `0..10` or `-10..-5, -4..4, 10..20`

#### Constraints

* Supported operations:
    * for int: `+`, `-`, `*`, `/`, `%`, `min`, `max`, `<`, `<=`, `>`, `>=`, `=`, `!=`
    * for bool: `!`, `|`, `&`, `^`, `<=>`, `->`, `ALO` (at least operation), `AMO` (at most operation)
    * unary minus for int negation
    * aliases for boolean operations: `or` for `|`, `and` for `&`, `xor` for `^`, `iff` for `<=>`, `=>` for `->`

#### Example
```
int x: -10..10
int y: -10..10
int z: -10..10
x * x + y * y + z * z < 100
x + y + -z = 5
```
