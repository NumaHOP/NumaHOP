package fr.progilone.pgcn.service.exchange.marc.mapping;

import fr.progilone.pgcn.service.exchange.marc.script.CustomScript;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.script.CompiledScript;
import org.marc4j.converter.CharConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Représente le script lié à une condition ou une expression
 * <p>
 * Created by Sebastien on 06/12/2016.
 */
public class CompiledStatement {

	private static final Logger LOG = LoggerFactory.getLogger(CompiledStatement.class);

	// Cette regex extrait les sous-chaines de la forme \<tag>$<code> et \<tag>,
	// représentant des champs d'une notice MARC
	private static final Pattern EXPRESSION_PATTERN = Pattern
		.compile("(?:\\\\)(?<tag>[x\\d]{3}(?!\\d))(?:(?!\\d)\\$(?<code>[0-9a-zA-Z]))?");

	/**
	 * Champs MARC utilisés dans ce script
	 */
	private final Set<MarcKey> marcKeys = new HashSet<>();

	/**
	 * Script de configuration de ce statement
	 */
	private String configScript;

	/**
	 * Script saisi dans le mapping
	 */
	private String userScript;

	/**
	 * Script d'initialisation, nécessaire pour l'exécution de userScript
	 */
	private String initScript;

	/**
	 * Script principal compilé
	 */
	private CompiledScript compiledScript;

	/**
	 * {@link CustomScript} utilisés dans ce script
	 */
	private final List<CustomScript> customScripts = new ArrayList<>();

	public CompiledStatement(final String statement, final String configStatement) {
		this.configScript = configStatement;
		this.userScript = statement;
		this.marcKeys.addAll(getMarcKeys(statement));
	}

	public Set<MarcKey> getMarcKeys() {
		return marcKeys;
	}

	public String getConfigScript() {
		return configScript;
	}

	public String getInitScript() {
		return initScript;
	}

	public String getUserScript() {
		return userScript;
	}

	public List<CustomScript> getCustomScripts() {
		return customScripts;
	}

	public CompiledScript getCompiledScript() {
		return compiledScript;
	}

	public void setCompiledScript(final CompiledScript compiledScript) {
		this.compiledScript = compiledScript;
	}

	/**
	 * Initialisation des scripts personnalisés
	 * @return
	 */
	public void initialize(CharConverter charConverter) {
		if (this.userScript != null) {
			final Set<String> scriptsImports = new HashSet<>();
			final List<String> initScripts = new ArrayList<>();

			// Ajout des customScripts
			CustomScript.getScripts().forEach((scriptName, fmtClass) -> {
				// le script peut être multilignes: (?s).* matche tous les caractères, y
				// compris les sauts de ligne
				if (this.userScript.matches("^(?s).*\\b" + scriptName + "\\((?s).*$")) {
					try {
						final CustomScript customScript = fmtClass.getConstructor(String.class, CharConverter.class)
							.newInstance(scriptName.toLowerCase(), charConverter);
						scriptsImports.addAll(Arrays.asList(customScript.getScriptImport()));
						initScripts.add(customScript.getInitScript());
						this.customScripts.add(customScript);
					}
					catch (ReflectiveOperationException e) {
						LOG.error(e.getMessage(), e);
					}
				}
			});
			// Construction du script d'initialisation
			if (!scriptsImports.isEmpty() || !initScripts.isEmpty()) {
				final StringBuilder scrBuilder = new StringBuilder();
				scriptsImports.forEach(scr -> scrBuilder.append(scr).append('\n'));
				initScripts.forEach(scr -> scrBuilder.append(scr).append('\n'));
				this.initScript = scrBuilder.toString();
			}
		}
	}

	/**
	 * Recherche les tag$code présents dans le statement passé en paramètre (expression ou
	 * condition d'une règle de mapping)
	 * @param statement
	 * @return
	 */
	private Set<MarcKey> getMarcKeys(final String statement) {
		if (statement == null) {
			return Collections.emptySet();
		}
		final Set<MarcKey> innerMarcKeys = new HashSet<>();
		final Matcher matcher = EXPRESSION_PATTERN.matcher(statement);

		while (matcher.find()) {
			final String tag = matcher.group("tag");
			final String code = matcher.group("code");
			innerMarcKeys.add(code != null ? new MarcKey(tag, code.charAt(0)) : new MarcKey(tag));
		}
		return innerMarcKeys;
	}

}
