import { useEffect, useMemo, useState } from 'react'
import api from '../services/api'

type SortOrder = 'none' | 'priceAsc' | 'priceDesc'

type Pc = {
    id: number
    nome: string
    prezzo: number
}

function CatalogPage() {
    const [pcs, setPcs] = useState<Pc[]>([])
    const [searchName, setSearchName] = useState('')
    const [minPrice, setMinPrice] = useState('')
    const [maxPrice, setMaxPrice] = useState('')
    const [loading, setLoading] = useState(true)
    const [error, setError] = useState('')
    const [sortOrder, setSortOrder] = useState<SortOrder>('none')

    useEffect(() => {
        api.get<Pc[]>('/api/catalog')
            .then(response => {
                setPcs(response.data)
                setError('')
            })
            .catch(() => {
                setError('Errore durante il caricamento del catalogo.')
            })
            .finally(() => {
                setLoading(false)
            })
    }, [])

    const filteredPcs = useMemo(() => {
        const normalizedSearch = searchName.trim().toLowerCase()

        const min = minPrice.trim() === '' ? null : Number(minPrice)
        const max = maxPrice.trim() === '' ? null : Number(maxPrice)

        const filtered = pcs.filter(pc => {
            const matchesName = pc.nome.toLowerCase().includes(normalizedSearch)

            const matchesMinPrice =
                min === null || pc.prezzo >= min

            const matchesMaxPrice =
                max === null || pc.prezzo <= max

            return matchesName && matchesMinPrice && matchesMaxPrice
        })

        const sorted = [...filtered].sort((a, b) => {
            if (sortOrder === 'priceAsc') {
                return a.prezzo - b.prezzo
            }

            if (sortOrder === 'priceDesc') {
                return b.prezzo - a.prezzo
            }

            return 0
        })

        return sorted
    }, [pcs, searchName, minPrice, maxPrice, sortOrder])

    function getPcImageUrl(pcId: number) {
        const baseUrl = api.defaults.baseURL ?? ''

        const backendBaseUrl = baseUrl.endsWith('/api')
            ? baseUrl.slice(0, -4)
            : baseUrl

        return `${backendBaseUrl}/pc/image/${pcId}`
    }

    function resetFilters() {
        setSearchName('')
        setMinPrice('')
        setMaxPrice('')
        setSortOrder('none')
    }

    function formatPrice(price: number) {
        return price.toLocaleString('it-IT', {
            minimumFractionDigits: 2,
            maximumFractionDigits: 2
        }) + ' €'
    }

    return (
        <>
            <header>
                <div className="logo">
                    PC Express
                </div>

                <nav className="nav_header">
                    <a href="/" className="nav_links">Home</a>
                    <a href="/user/cart" className="nav_links">Carrello</a>
                    <a href="/sale/user_sales" className="nav_links">I miei acquisti</a>
                    <a href="/user/personal_area" className="nav_links">Area Personale</a>
                </nav>
            </header>

            <main className="catalog-page">

                <section className="catalog-hero">
                    <div>
                        <h1>Catalogo PC</h1>
                        <p>Scopri i nostri PC preassemblati per gaming, studio, lavoro e produttività.</p>
                    </div>
                </section>

                <section className="catalog-container">

                    <div className="back-link">
                        <a href="/">← Torna alla home</a>
                    </div>

                    <div className="catalog-filters">
                        <div className="filter-group filter-search">
                            <label htmlFor="searchName">Cerca per nome</label>
                            <input
                                type="text"
                                id="searchName"
                                value={searchName}
                                onChange={event => setSearchName(event.target.value)}
                                placeholder="Cerca un PC"
                            />
                        </div>

                        <div className="filter-group">
                            <label htmlFor="minPrice">Prezzo minimo</label>
                            <input
                                type="number"
                                id="minPrice"
                                value={minPrice}
                                onChange={event => setMinPrice(event.target.value)}
                                min="0"
                                step="0.01"
                            />
                        </div>

                        <div className="filter-group">
                            <label htmlFor="maxPrice">Prezzo massimo</label>
                            <input
                                type="number"
                                id="maxPrice"
                                value={maxPrice}
                                onChange={event => setMaxPrice(event.target.value)}
                                min="0"
                                step="0.01"
                            />
                        </div>

                        <div className="filter-group">
                            <label htmlFor="sortOrder">Ordina per prezzo</label>
                            <select
                                id="sortOrder"
                                value={sortOrder}
                                onChange={event => setSortOrder(event.target.value as SortOrder)}
                            >
                                <option value="none">Nessun ordinamento</option>
                                <option value="priceAsc">Prezzo crescente</option>
                                <option value="priceDesc">Prezzo decrescente</option>
                            </select>
                        </div>

                        <button type="button" className="reset-filters-button" onClick={resetFilters}>
                            Cancella filtri
                        </button>
                    </div>

                    {loading && (
                        <div className="empty-catalog">
                            <h2>Caricamento catalogo</h2>
                            <p>Stiamo recuperando i PC disponibili.</p>
                        </div>
                    )}

                    {!loading && error !== '' && (
                        <div className="empty-catalog">
                            <h2>Errore</h2>
                            <p>{error}</p>
                        </div>
                    )}

                    {!loading && error === '' && pcs.length === 0 && (
                        <div className="empty-catalog">
                            <h2>Nessun PC disponibile</h2>
                            <p>Al momento non ci sono computer nel catalogo.</p>
                        </div>
                    )}

                    {!loading && error === '' && pcs.length > 0 && filteredPcs.length === 0 && (
                        <div className="empty-catalog">
                            <h2>Nessun risultato trovato</h2>
                            <p>Non ci sono PC corrispondenti ai filtri selezionati.</p>
                        </div>
                    )}

                    {!loading && error === '' && filteredPcs.length > 0 && (
                        <div className="pc-grid">
                            {filteredPcs.map(pc => (
                                <article className="pc-card" key={pc.id}>

                                    <a className="pc-image-box" href={`/pc/${pc.id}`}>
                                        <img src={getPcImageUrl(pc.id)} alt={pc.nome} />
                                    </a>

                                    <div className="pc-card-content">
                                        <a className="pc-name" href={`/pc/${pc.id}`}>
                                            {pc.nome}
                                        </a>

                                        <div className="pc-price">
                                            {formatPrice(pc.prezzo)}
                                        </div>

                                        <p>Configurazione selezionata da PC Express, pronta per l’utilizzo.</p>

                                        <a className="details-button" href={`/pc/${pc.id}`}>
                                            Vedi dettagli
                                        </a>
                                    </div>

                                </article>
                            ))}
                        </div>
                    )}

                </section>

            </main>

            <footer>
                <p>PC Express - Il tuo negozio di PC preassemblati.</p>
            </footer>
        </>
    )
}

export default CatalogPage