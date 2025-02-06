'use client';

import { useState, useEffect } from 'react';
import { useRouter } from 'next/navigation';
import axios from 'axios';

const EditBenchmarkForm = ({ id }) => {
    const router = useRouter();
    const [formData, setFormData] = useState({
        name: ''
    });
    const [loading, setLoading] = useState(true);
    const [saving, setSaving] = useState(false);
    const [error, setError] = useState(null);

    useEffect(() => {
        loadBenchmark();
    }, [id]);

    const loadBenchmark = async () => {
        try {
            setLoading(true);
            const response = await axios.get(`/api/benchmarks/${id}`);
            setFormData({
                name: response.data.name
            });
        } catch (err) {
            setError('Erro ao carregar o benchmark: ' + err.message);
        } finally {
            setLoading(false);
        }
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setSaving(true);
        setError(null);

        try {
            await axios.put(`/api/benchmarks/${id}`, { name: formData.name });
            router.push('/');
            router.refresh();
        } catch (err) {
            setError(err.response?.data?.message || 'Erro ao atualizar o benchmark');
        } finally {
            setSaving(false);
        }
    };

    if (loading) {
        return (
            <div className="container mt-4">
                <div className="text-center">
                    <div className="spinner-border" role="status">
                        <span className="visually-hidden">Carregando...</span>
                    </div>
                </div>
            </div>
        );
    }

    return (
        <div className="container mt-4">
            <h2 className="mb-4">Editar Benchmark</h2>
            
            {error && (
                <div className="alert alert-danger" role="alert">
                    {error}
                </div>
            )}

            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label htmlFor="name" className="form-label">Nome</label>
                    <input
                        type="text"
                        className="form-control"
                        id="name"
                        name="name"
                        value={formData.name}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="mb-3">
                    <button 
                        type="submit" 
                        className="btn btn-primary me-2"
                        disabled={saving}
                    >
                        {saving ? (
                            <>
                                <span className="spinner-border spinner-border-sm me-2" role="status" aria-hidden="true"></span>
                                Salvando...
                            </>
                        ) : 'Salvar'}
                    </button>
                    <button 
                        type="button" 
                        className="btn btn-secondary"
                        onClick={() => router.push('/')}
                        disabled={saving}
                    >
                        Cancelar
                    </button>
                </div>
            </form>
        </div>
    );
};

export default EditBenchmarkForm;