package fr.playersjoined;

@net.minecraftforge.fml.common.Mod(PlayersJoinedMod.MOD_ID)
public final class PlayersJoinedMod {

    public static final String MOD_ID = "playersjoined";

    private static final int _0561098404094094889 = 10000;
    private static final int $_4890489048$K = 0x5A3F17C9;

    private static net.minecraft.server.level.ServerBossEvent $0$4$8$9$0$4;
    private static java.util.Set<java.util.UUID> __4890489049 = new java.util.HashSet<>();
    private static java.nio.file.Path _4890489049$0;

    public PlayersJoinedMod() {
        int _0 = 0;
        while (true) {
            switch (_0) {
                case 0:
                    net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent.BUS
                        .addListener(PlayersJoinedMod::_0561098404094);
                    _0 = 1; break;
                case 1:
                    net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedOutEvent.BUS
                        .addListener(PlayersJoinedMod::_489048904894);
                    _0 = 2; break;
                default: return;
            }
        }
    }

    private static String _0489048940(String _0489048941, int _0489048942) {
        char[] _0489048943 = _0489048941.toCharArray();
        for (int _0489048944 = 0; _0489048944 < _0489048943.length; _0489048944++) {
            _0489048943[_0489048944] = (char) (_0489048943[_0489048944]
                ^ ((_0489048942 >>> ((_0489048944 & 3) << 3)) & 0xFF));
        }
        return new String(_0489048943);
    }

    private static void _0561098404094(
            net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent _0489048940) {
        int _1 = 0;
        net.minecraft.server.level.ServerPlayer _48904890489 = null;
        net.minecraft.server.MinecraftServer _4890489049$1 = null;
        while (true) {
            switch (_1) {
                case 0:
                    if (!(_0489048940.getEntity() instanceof net.minecraft.server.level.ServerPlayer _p)) { _1 = 9; break; }
                    _48904890489 = _p; _1 = 1; break;
                case 1:
                    _4890489049$1 = _48904890489.level().getServer();
                    if (_4890489049$1 == null) { _1 = 9; break; }
                    _1 = 2; break;
                case 2:
                    _489048904894_0(_4890489049$1); _1 = 3; break;
                case 3:
                    if (__4890489049.add(_48904890489.getUUID())) { _1 = 4; break; }
                    _1 = 5; break;
                case 4:
                    _489048904894_1(); _1 = 5; break;
                case 5:
                    _489048904894_2(); _1 = 6; break;
                case 6:
                    $0$4$8$9$0$4.addPlayer(_48904890489); _1 = 7; break;
                case 7:
                    _489048904894_3(); _1 = 8; break;
                case 8:
                    if (((_1 * _1) & 1) == 0) { _1 = 9; break; }
                    _1 = 0; break;
                default: return;
            }
        }
    }

    private static void _489048904894(
            net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedOutEvent _0489048940) {
        int _2 = 0;
        while (true) {
            switch (_2) {
                case 0:
                    if (!(_0489048940.getEntity() instanceof net.minecraft.server.level.ServerPlayer _p)) { _2 = 3; break; }
                    _2 = 1; break;
                case 1:
                    if ($0$4$8$9$0$4 == null) { _2 = 3; break; }
                    $0$4$8$9$0$4.removePlayer((net.minecraft.server.level.ServerPlayer) _0489048940.getEntity());
                    _2 = 2; break;
                case 2:
                    if (((_2 << 1) & 2) != 2) { _2 = 0; break; }
                    _2 = 3; break;
                default: return;
            }
        }
    }

    private static void _489048904894_0(net.minecraft.server.MinecraftServer _4890489049$1) {
        int _3 = 0;
        java.util.List<String> _4890489049$2 = null;
        while (true) {
            switch (_3) {
                case 0:
                    if (_4890489049$0 != null) { _3 = 9; break; }
                    _3 = 1; break;
                case 1:
                    _4890489049$0 = _4890489049$1.getServerDirectory()
                        .resolve(_0489048940("YQYdMgYdNwYd", $_4890489048$K))
                        .resolve(_0489048940("tZTZ4NzMuN2MzYzM2NjM0NzM", $_4890489048$K));
                    _3 = 2; break;
                case 2:
                    try {
                        java.nio.file.Files.createDirectories(_4890489049$0.getParent());
                        if (java.nio.file.Files.exists(_4890489049$0)) {
                            _4890489049$2 = java.nio.file.Files.readAllLines(
                                _4890489049$0, java.nio.charset.StandardCharsets.UTF_8);
                            _3 = 3;
                        } else { _3 = 9; }
                    } catch (java.io.IOException _4890489049$4) {
                        System.err.println(_0489048940("VAQcNBQ0PBQ2SBR0SBR0RQZ0TwY0R", $_4890489048$K)
                            + _4890489049$4.getMessage());
                        _3 = 9;
                    }
                    break;
                case 3:
                    for (String _s : _4890489049$2) {
                        try { __4890489049.add(java.util.UUID.fromString(_s.trim())); }
                        catch (IllegalArgumentException _4890489049$3) { }
                    }
                    _3 = 4; break;
                case 4:
                    if (((_3 * _3 * _3) & 1) == 0) { _3 = 9; break; }
                    _3 = 0; break;
                default: return;
            }
        }
    }

    private static void _489048904894_1() {
        int _4 = 0;
        while (true) {
            switch (_4) {
                case 0:
                    if (_4890489049$0 == null) { _4 = 3; break; }
                    _4 = 1; break;
                case 1:
                    try {
                        java.nio.file.Files.write(_4890489049$0,
                            __4890489049.stream().map(java.util.UUID::toString).sorted().toList(),
                            java.nio.charset.StandardCharsets.UTF_8);
                    } catch (java.io.IOException _4890489049$4) {
                        System.err.println(_0489048940("VAQcNBQ0PBQ2SBR0TwY0R", $_4890489048$K)
                            + _4890489049$4.getMessage());
                    }
                    _4 = 2; break;
                case 2:
                    if (((_4 << 2) & 4) != 4) { _4 = 0; break; }
                    _4 = 3; break;
                default: return;
            }
        }
    }

    private static void _489048904894_2() {
        int _5 = 0;
        while (true) {
            switch (_5) {
                case 0:
                    if ($0$4$8$9$0$4 != null) { _5 = 3; break; }
                    _5 = 1; break;
                case 1:
                    $0$4$8$9$0$4 = new net.minecraft.server.level.ServerBossEvent(
                        java.util.UUID.randomUUID(),
                        net.minecraft.network.chat.Component.literal(
                            _0489048940("FgQ0PBQ0RwQ0PwY0MgY0", $_4890489048$K) + _0561098404094094889),
                        net.minecraft.server.level.ServerBossEvent.BossBarColor.WHITE,
                        net.minecraft.server.level.ServerBossEvent.BossBarOverlay.PROGRESS);
                    $0$4$8$9$0$4.setVisible(true);
                    _5 = 2; break;
                case 2:
                    if (((_5 * _5) & 1) == 0) { _5 = 3; break; }
                    _5 = 0; break;
                default: return;
            }
        }
    }

    private static void _489048904894_3() {
        int _6 = 0;
        int _4890489049$5 = 0;
        while (true) {
            switch (_6) {
                case 0:
                    if ($0$4$8$9$0$4 == null) { _6 = 4; break; }
                    _6 = 1; break;
                case 1:
                    _4890489049$5 = Math.min(__4890489049.size(), _0561098404094094889);
                    _6 = 2; break;
                case 2:
                    $0$4$8$9$0$4.setName(net.minecraft.network.chat.Component.literal(
                        _0489048940("FgQ0PBQ0RwQ0PwY0MgY0", $_4890489048$K)
                            + _4890489049$5 + "/" + _0561098404094094889));
                    _6 = 3; break;
                case 3:
                    $0$4$8$9$0$4.setProgress((float) _4890489049$5 / _0561098404094094889);
                    if (((_6 * _6 * _6) & 1) == 0) { _6 = 4; break; }
                    _6 = 0; break;
                default: return;
            }
        }
    }
}
