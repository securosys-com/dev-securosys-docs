---
sidebar_position: 2
title: Supported Cryptocurrencies
sidebar_label: Supported Cryptocurrencies
description: List of top cryptocurrencies and their signing algorithms and curves.
keywords: [cryptocurrencies, ECDSA, secp256k1, EdDSA, curve25519, Polkadot, Tezos, Bitcoin, Ethereum, Stellar, Cardano, Elrond, data security, key management, cloud hsm, hsm key management, hsm cloud, hsm as a service, cloud based hsm, hsm digital signature, hsm services, hsm service, hsm, hardware security module]
---

# Supported Cryptocurrencies

We took a snapshot of rankings by market cap in early February 2021. In summary, **74** coins use ECDSA and the secp256k1 curve, including Bitcoin, Ethereum, and 48 ERC20 tokens. **10** coins use EdDSA and curve25519, such as Stellar, Cardano and Elrond. **8** coins use multiple signing algorithms and curves (often both ECDSA/secp256k1 and EdDSA/curve25519), such as Polkadot and Tezos.

Here is a list of the top 100 currencies, their Signing Algorithm and Curve:

<table>
            <tbody>
                <tr>
                    <th class="title">Name</th>
                    <th class="title symbol">Symbol</th>
                    <th class="title">Signing Algorithm</th>
                    <th class="title">Curve</th>
                    <th class="title notes">Notes</th>
                </tr>
                <tr>
                    <td><b>Bitcoin</b></td>
                    <td>BTC</td>
                    <td>ECDSA</td>
                    <td>secp256k1</td>
                    <td></td>
                </tr>
                <tr class="colored">
                    <td><b>Ethereum</b></td>
                    <td>ETH</td>
                    <td>ECDSA</td>
                    <td>secp256k1</td>
                    <td></td>
                </tr>
                <tr>
                    <td><b>Tether</b></td>
                    <td>USDT</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr class="colored">
                    <td><b>Polkadot</b></td>
                    <td>DOT</td>
                    <td>ECDSA, Schnorr, EdDSA</td>
                    <td>secp256k1, ristretto25519, curve25519</td>
                    <td></td>
                </tr>
                <tr>
                    <td><b>XRP</b></td>
                    <td>XRP</td>
                    <td>ECDSA, EdDSA</td>
                    <td>curve25519, secp256k1*</td>
                    <td><b>*</b> is the default</td>
                </tr>
                <tr class="colored">
                    <td><b>Cardano</b></td>
                    <td>ADA</td>
                    <td>EdDSA</td>
                    <td>curve25519</td>
                    <td></td>
                </tr>
                <tr>
                    <td><b>Litecoin</b></td>
                    <td>LTC</td>
                    <td>ECDSA</td>
                    <td>secp256k1</td>
                    <td></td>
                </tr>
                <tr class="colored">
                    <td><b>Bitcoin Cash</b></td>
                    <td>BCH</td>
                    <td>ECDSA</td>
                    <td>secp256k1</td>
                    <td></td>
                </tr>
                <tr>
                    <td><b>Chainlink</b></td>
                    <td>LINK</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr class="colored">
                    <td><b>Stellar</b></td>
                    <td>XLM</td>
                    <td>EdDSA</td>
                    <td>curve25519</td>
                    <td></td>
                </tr>
                <tr>
                    <td><b>Binance Coin</b></td>
                    <td>BNB</td>
                    <td>ECDSA</td>
                    <td>secp256k1</td>
                    <td></td>
                </tr>
                <tr class="colored">
                    <td><b>USD Coin</b></td>
                    <td>USDC</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr>
                    <td><b>Wrapped Bitcoin</b></td>
                    <td>WBTC</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr class="colored">
                    <td><b>Bitcoin SV</b></td>
                    <td>BSV</td>
                    <td>ECDSA</td>
                    <td>secp256k1</td>
                    <td></td>
                </tr>
                <tr>
                    <td><b>EOS</b></td>
                    <td>EOS</td>
                    <td>ECDSA</td>
                    <td>secp256k1</td>
                    <td></td>
                </tr>
                <tr class="colored">
                    <td><b>Monero</b></td>
                    <td>XMR</td>
                    <td>EdDSA*, Bulletproofs</td>
                    <td>curve25519</td>
                    <td>* non-standard hashing algorithm, uses Keccak</td>
                </tr>
                <tr>
                    <td><b>Aave</b></td>
                    <td>AAVE</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr class="colored">
                    <td><b>Tron</b></td>
                    <td>TRX</td>
                    <td>ECDSA</td>
                    <td>secp256k1</td>
                    <td></td>
                </tr>
                <tr>
                    <td><b>VeChain</b></td>
                    <td>VET</td>
                    <td>ECDSA</td>
                    <td>secp256k1</td>
                    <td></td>
                </tr>
                <tr class="colored">
                    <td><b>NEM</b></td>
                    <td>XEM</td>
                    <td>EdDSA</td>
                    <td>curve25519</td>
                    <td></td>
                </tr>
                <tr>
                    <td><b>Synthetix</b></td>
                    <td>SNX</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr class="colored">
                    <td><b>Cosmos</b></td>
                    <td>ATOM</td>
                    <td>ECDSA</td>
                    <td>secp256k1</td>
                    <td></td>
                </tr>
                <tr>
                    <td><b>Theta Network</b></td>
                    <td>THETA</td>
                    <td>ECDSA</td>
                    <td>secp256k1</td>
                    <td></td>
                </tr>
                <tr class="colored">
                    <td><b>Uniswap</b></td>
                    <td>UNI</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr>
                    <td><b>NEO</b></td>
                    <td>NEO</td>
                    <td>ECDSA</td>
                    <td>NIST P-256</td>
                    <td></td>
                </tr>
                <tr class="colored">
                    <td><b>Crypto.com Coin</b></td>
                    <td>CRO</td>
                    <td>ECDSA</td>
                    <td>secp256k1</td>
                    <td></td>
                </tr>
                <tr>
                    <td><b>OKB</b></td>
                    <td>OKB</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr class="colored">
                    <td><b>Celsius Network</b></td>
                    <td>CEL</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr>
                    <td><b>cUSDC</b></td>
                    <td>CUSDC</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr class="colored">
                    <td><b>cETH</b></td>
                    <td>CETH</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr>
                    <td><b>Dai</b></td>
                    <td>DAI</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr class="colored">
                    <td><b>LEO Token</b></td>
                    <td>LEO</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr>
                    <td><b>IOTA</b></td>
                    <td>MIOTA</td>
                    <td>Winternitz OTS</td>
                    <td></td>
                    <td></td>
                </tr>
                <tr class="colored">
                    <td><b>Tezos</b></td>
                    <td>XTZ</td>
                    <td>ECDSA, EdDSA</td>
                    <td>secp256k1, curve25519, NIST P-256</td>
                    <td>tz1: EdDSA, tz2: ECDSA/secp256k1, tz3: ECDSA/NIST P-256</td>
                </tr>
                <tr>
                    <td><b>Elrond</b></td>
                    <td>EGLD</td>
                    <td>EdDSA</td>
                    <td>curve25519</td>
                    <td></td>
                </tr>
                <tr class="colored">
                    <td><b>Maker</b></td>
                    <td>MKR</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr>
                    <td><b>Binance USD</b></td>
                    <td>BUSD</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr class="colored">
                    <td><b>Dogecoin</b></td>
                    <td>DOGE</td>
                    <td>ECDSA</td>
                    <td>secp256k1</td>
                    <td></td>
                </tr>
                <tr>
                    <td><b>Dash</b></td>
                    <td>DASH</td>
                    <td>ECDSA</td>
                    <td>secp256k1</td>
                    <td></td>
                </tr>
                <tr class="colored">
                    <td><b>Huobi Token</b></td>
                    <td>HT</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr>
                    <td><b>Filecoin</b></td>
                    <td>FIL</td>
                    <td>ECDSA</td>
                    <td>secp256k1</td>
                    <td></td>
                </tr>
                <tr class="colored">
                    <td><b>Zcash</b></td>
                    <td>ZEC</td>
                    <td>ECDSA, ZK-SNARKs*</td>
                    <td>secp256k1, BLS12-381-JubJub*</td>
                    <td>* for shielded/anonymous transactions</td>
                </tr>
                <tr>
                    <td><b>Avalanche</b></td>
                    <td>AVAX</td>
                    <td>ECDSA</td>
                    <td>secp256k1</td>
                    <td></td>
                </tr>
                <tr class="colored">
                    <td><b>Yearn Finance</b></td>
                    <td>YFI</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr>
                    <td><b>Kusama</b></td>
                    <td>KSM</td>
                    <td>ECDSA, EdDSA, Schnorr</td>
                    <td>secp256k1, curve25519, ristretto25519</td>
                    <td></td>
                </tr>
                <tr class="colored">
                    <td><b>Ethereum Classic</b></td>
                    <td>ETC</td>
                    <td>ECDSA</td>
                    <td>secp256k1</td>
                    <td></td>
                </tr>
                <tr>
                    <td><b>Solana</b></td>
                    <td>SOL</td>
                    <td>EdDSA</td>
                    <td>curve25519</td>
                    <td></td>
                </tr>
                <tr class="colored">
                    <td><b>cDAI</b></td>
                    <td>CDAI</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr>
                    <td><b>FTX Token</b></td>
                    <td>FTT</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr class="colored">
                    <td><b>Sushi</b></td>
                    <td>SUSHI</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr>
                    <td><b>Compound</b></td>
                    <td>COMP</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr class="colored">
                    <td><b>Huobi BTC</b></td>
                    <td>HBTC</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr>
                    <td><b>Zilliqa</b></td>
                    <td>ZIL</td>
                    <td>EC-Schnorr</td>
                    <td>secp256k1</td>
                    <td></td>
                </tr>
                <tr class="colored">
                    <td><b>Hedera Hashgraph</b></td>
                    <td>HBAR</td>
                    <td>ECDSA, EdDSA, RSA*</td>
                    <td>NIST P-384, curve25519</td>
                    <td>* 3072 bit RSA</td>
                </tr>
                <tr>
                    <td><b>UMA</b></td>
                    <td>UMA</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr class="colored">
                    <td><b>Decred</b></td>
                    <td>DCR</td>
                    <td>EdDSA, EC-Schnorr*</td>
                    <td>curve25519, secp256k1</td>
                    <td>* non-standard</td>
                </tr>
                <tr>
                    <td><b>Waves</b></td>
                    <td>WAVES</td>
                    <td>EdDSA</td>
                    <td>curve25519</td>
                    <td></td>
                </tr>
                <tr class="colored">
                    <td><b>Near</b></td>
                    <td>NEAR</td>
                    <td>ECDSA, EdDSA</td>
                    <td>secp256k1, curve25519</td>
                    <td></td>
                </tr>
                <tr>
                    <td><b>The Graph</b></td>
                    <td>GRT</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr class="colored">
                    <td><b>REN</b></td>
                    <td>REN</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr>
                    <td><b>Paxos Standard</b></td>
                    <td>PAX</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr class="colored">
                    <td><b>Loopring</b></td>
                    <td>LRC</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr>
                    <td><b>OMG Network</b></td>
                    <td>OMG</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr class="colored">
                    <td><b>renBTC</b></td>
                    <td>RENBTC</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr>
                    <td><b>Ontology</b></td>
                    <td>ONT</td>
                    <td>ECDSA</td>
                    <td>NIST P-256</td>
                    <td></td>
                </tr>
                <tr class="colored">
                    <td><b>ICON</b></td>
                    <td>ICX</td>
                    <td>ECDSA</td>
                    <td>secp256k1</td>
                    <td></td>
                </tr>
                <tr>
                    <td><b>Blockstack</b></td>
                    <td>STX</td>
                    <td>ECDSA</td>
                    <td>secp256k1</td>
                    <td></td>
                </tr>
                <tr class="colored">
                    <td><b>THORChain</b></td>
                    <td>RUNE</td>
                    <td colspan="3" class="erc20">Binance BEP2 token</td>
                </tr>
                <tr>
                    <td><b>TrueUSD</b></td>
                    <td>TUSD</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr class="colored">
                    <td><b>Nano</b></td>
                    <td>NANO</td>
                    <td>EdDSA*</td>
                    <td>curve25519</td>
                    <td>* uses Blake2b-512 instead of SHA-512 in key derivation</td>
                </tr>
                <tr>
                    <td><b>Terra</b></td>
                    <td>LUNA</td>
                    <td>ECDSA</td>
                    <td>secp256k1</td>
                    <td></td>
                </tr>
                <tr class="colored">
                    <td><b>NEXO</b></td>
                    <td>NEXO</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr>
                    <td><b>Algorand</b></td>
                    <td>ALGO</td>
                    <td>EdDSA</td>
                    <td>curve25519</td>
                    <td></td>
                </tr>
                <tr class="colored">
                    <td><b>Basic Attention Token</b></td>
                    <td>BAT</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr>
                    <td><b>0x</b></td>
                    <td>ZRX</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr class="colored">
                    <td><b>DigiByte</b></td>
                    <td>DGB</td>
                    <td>ECDSA</td>
                    <td>secp256k1</td>
                    <td></td>
                </tr>
                <tr>
                    <td><b>Reserve Rights Token</b></td>
                    <td>RSR</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr class="colored">
                    <td><b>Curve DAO Token</b></td>
                    <td>CRV</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr>
                    <td><b>Enjin Coin</b></td>
                    <td>OMG</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr class="colored">
                    <td><b>Aave Link</b></td>
                    <td>ALINK</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr>
                    <td><b>Nexus Mutual</b></td>
                    <td>NXM</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr class="colored">
                    <td><b>Horizen</b></td>
                    <td>ZEN</td>
                    <td>ECDSA</td>
                    <td>secp256k1</td>
                    <td></td>
                </tr>
                <tr>
                    <td><b>HUSD</b></td>
                    <td>HUSD</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr class="colored">
                    <td><b>IOST</b></td>
                    <td>IOUST</td>
                    <td>ECDSA, EdDSA</td>
                    <td>secp256k1, curve25519</td>
                    <td></td>
                </tr>
                <tr>
                    <td><b>Qtum</b></td>
                    <td>QTUM</td>
                    <td>ECDSA</td>
                    <td>secp256k1</td>
                    <td></td>
                </tr>
                <tr class="colored">
                    <td><b>Kyber Network</b></td>
                    <td>KNC</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr>
                    <td><b>Bitcoin Cash ABC</b></td>
                    <td>BCHA</td>
                    <td>ECDSA</td>
                    <td>secp256k1</td>
                    <td></td>
                </tr>
                <tr class="colored">
                    <td><b>Energy Web Token</b></td>
                    <td>EWT</td>
                    <td>ECDSA</td>
                    <td>secp256k1</td>
                    <td></td>
                </tr>
                <tr>
                    <td><b>SwissBorg</b></td>
                    <td>CHSB</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr class="colored">
                    <td><b>Ampleforth</b></td>
                    <td>AMPL</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr>
                    <td><b>BitTorrent</b></td>
                    <td>BTT</td>
                    <td colspan="3" class="erc20">Tron TRC20 token</td>
                </tr>
                <tr class="colored">
                    <td><b>Quant</b></td>
                    <td>QNT</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr>
                    <td><b>Balancer</b></td>
                    <td>BAL</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr class="colored">
                    <td><b>Siacoin</b></td>
                    <td>SC</td>
                    <td>EdDSA</td>
                    <td>curve25519</td>
                    <td></td>
                </tr>
                <tr>
                    <td><b>Ocean Protocol</b></td>
                    <td>OCEAN</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr class="colored">
                    <td><b>Aave ETH</b></td>
                    <td>AETH</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr>
                    <td><b>TerraUSD</b></td>
                    <td>UST</td>
                    <td>ECDSA</td>
                    <td>secp256k1</td>
                    <td></td>
                </tr>
                <tr class="colored">
                    <td><b>Voyager Token</b></td>
                    <td>VGX</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
                <tr>
                    <td><b>Arweave</b></td>
                    <td>AR</td>
                    <td>RSA*</td>
                    <td></td>
                    <td>* 4096 bit RSA</td>
                </tr>
                <tr class="colored">
                    <td><b>Status</b></td>
                    <td>SNT</td>
                    <td colspan="3" class="erc20">Ethereum ERC20 token</td>
                </tr>
            </tbody>
        </table>

_Source [ethanfast.com](http://ethanfast.com/top-crypto.html)_





