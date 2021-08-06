// EQNSET96 => "EQN_AVC_Standard";
parameter spec Tim_variables_eqn96 {
  // ----- For digInOut variables ------
  // For port @
  var Double numer_eqn96;
  var Double denom_eqn96;
  var Double var_eqn96;
  var Double offset_eqn96;
  var Time per_eqn96 = (numer_eqn96 / denom_eqn96 * var_eqn96) * 1 ns;
}
